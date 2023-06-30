package br.com.carlosjunior.msavaliadorcredito.domain.rabbitmq;

import br.com.carlosjunior.msavaliadorcredito.domain.dto.DadosSolicitacaoEmissaoCartaoDTO;
import br.com.carlosjunior.msavaliadorcredito.util.JsonConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;

    public void solicitarCartao(DadosSolicitacaoEmissaoCartaoDTO dados) throws JsonProcessingException {
        String json = JsonConvert.toIntoJson(dados);
        log.info("[SolicitacaoEmissaoCartaoPublisher].solicitarCartao: "+dados.toString());
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);

    }


}
