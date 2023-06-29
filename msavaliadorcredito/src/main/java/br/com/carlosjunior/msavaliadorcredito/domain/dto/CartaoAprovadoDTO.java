package br.com.carlosjunior.msavaliadorcredito.domain.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovadoDTO {

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
