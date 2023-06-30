package br.com.carlosjunior.mscartoes.domain.rabbitmq;

import br.com.carlosjunior.mscartoes.domain.dto.DadosSolicitacaoEmissaoCartaoDTO;
import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoClienteRepository;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final CartaoClienteRepository cartaoClienteRepository;
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {

        try {
            log.info("[EmissaoCartaoSubscriber].[receberSolicitacaoEmissao] Inicio");
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartaoDTO dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoDTO.class);

            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            log.info("[EmissaoCartaoSubscriber].[receberSolicitacaoEmissao] pos cartao");

            CartaoCliente novoCartaoCliente = new CartaoCliente();
            novoCartaoCliente.setCartao(cartao);
            novoCartaoCliente.setCpf(dados.getCpf());
            novoCartaoCliente.setLimite(dados.getLimiteLiberado());

            novoCartaoCliente = cartaoClienteRepository.save(novoCartaoCliente);
            log.info("[EmissaoCartaoSubscriber].[receberSolicitacaoEmissao] cadastrar novo cartao: "+novoCartaoCliente.toString());
        }catch (JsonProcessingException e){
            log.error("[EmissaoCartaoSubscriber].[receberSolicitacaoEmissao] ERRO");

            e.printStackTrace();
        }


    }

}
