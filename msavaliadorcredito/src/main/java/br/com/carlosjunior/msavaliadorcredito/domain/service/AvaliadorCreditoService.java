package br.com.carlosjunior.msavaliadorcredito.domain.service;

import br.com.carlosjunior.msavaliadorcredito.domain.dto.CartaoAprovadoDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.dto.RetornoAvaliacaoClienteDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.CartaoClienteDTOFeign;
import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.DadosClienteDTOFeign;
import br.com.carlosjunior.msavaliadorcredito.domain.dto.SituacaoClienteDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.feign.CartoesFeign;
import br.com.carlosjunior.msavaliadorcredito.domain.feign.ClientesFeign;
import br.com.carlosjunior.msavaliadorcredito.exception.ErrorComunicationMicroservicesException;
import br.com.carlosjunior.msavaliadorcredito.exception.NotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientesFeign clientesFeing;
    private final CartoesFeign cartoesFeign;

    public SituacaoClienteDTO obterSituacaoCliente(String cpf)
            throws NotFoundException, ErrorComunicationMicroservicesException {
        try {
            log.info("[AvaliadorCreditoService].[obterSituacaoCliente] -> dadosCliente");
            ResponseEntity<DadosClienteDTOFeign> dadosClienteFeig = clientesFeing.dadosCliente(cpf);
            log.info("[AvaliadorCreditoService].[obterSituacaoCliente] -> findCartoesClienteByCpf");
            ResponseEntity<List<CartaoClienteDTOFeign>> listaCartoesCliente = cartoesFeign.findCartoesClienteByCpf(cpf);
            log.info("[AvaliadorCreditoService].[obterSituacaoCliente] -> exit");

            return SituacaoClienteDTO.builder()
                    .cliente(dadosClienteFeig.getBody())
                    .cartoes(listaCartoesCliente.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            Integer status = e.status();
            if (HttpStatus.NOT_FOUND.equals(status)) {
                throw new NotFoundException("Cliente não encontrado");
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }


    public RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda)
            throws NotFoundException, ErrorComunicationMicroservicesException {
        try {
            log.info("[AvaliadorCreditoService].[realizarAvaliacao] -> dadosCliente");
            ResponseEntity<DadosClienteDTOFeign> dadosClienteFeig = clientesFeing.dadosCliente(cpf);
            log.info("[AvaliadorCreditoService].[realizarAvaliacao] -> cartoesCliente");
            ResponseEntity<List<CartaoClienteDTOFeign>> cartoesClienteFeign = cartoesFeign.findCartoesRendaControlada(renda);
            log.info("[AvaliadorCreditoService].[realizarAvaliacao] -> calculando limite");

            List<CartaoAprovadoDTO> lista = cartoesClienteFeign.getBody().stream().map(cartao -> {
                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosClienteFeig.getBody().getIdade());

                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovadoDTO aprovado = new CartaoAprovadoDTO();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;

            }).collect(Collectors.toList());
             return new RetornoAvaliacaoClienteDTO(lista);
        } catch (FeignException.FeignClientException e) {
            Integer status = e.status();
            if (HttpStatus.NOT_FOUND.equals(status)) {
                throw new NotFoundException("Cliente não encontrado");
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }

}
