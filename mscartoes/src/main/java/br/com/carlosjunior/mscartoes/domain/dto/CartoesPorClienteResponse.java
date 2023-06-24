package br.com.carlosjunior.mscartoes.domain.dto;

import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartoesPorClienteResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public static CartoesPorClienteResponse fromModel(CartaoCliente model){
        return new CartoesPorClienteResponse(model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite());
    }

}
