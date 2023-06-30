package br.com.carlosjunior.msavaliadorcredito.api;

import br.com.carlosjunior.msavaliadorcredito.domain.dto.*;
import br.com.carlosjunior.msavaliadorcredito.domain.exception.ErroSolicitacaoCartaoException;
import br.com.carlosjunior.msavaliadorcredito.domain.exception.ErrorComunicationMicroservicesException;
import br.com.carlosjunior.msavaliadorcredito.domain.exception.NotFoundException;
import br.com.carlosjunior.msavaliadorcredito.domain.service.AvaliadorCreditoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping("/status")
    public String status() {
        return "Ok";
    }

    @GetMapping("/situacao-cliente")
    public ResponseEntity consultarSituacaoCliente(@RequestParam String cpf) {
        try {
            SituacaoClienteDTO situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity dadosAvaliacao(@RequestBody DadosAvaliacaoDTO dto) {
        try {
            RetornoAvaliacaoClienteDTO retorno = avaliadorCreditoService.realizarAvaliacao(dto.getCpf(), dto.getRenda());
            return ResponseEntity.ok(retorno);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("/solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartaoDTO dados) {
        try {
            ProtocoloSolicitacaoCartaoDTO protocolo = avaliadorCreditoService.solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(protocolo);
        } catch (ErroSolicitacaoCartaoException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
