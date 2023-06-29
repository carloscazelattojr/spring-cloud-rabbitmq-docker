package br.com.carlosjunior.msavaliadorcredito.domain.feign.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoClienteDTOFeign {

    private String Nome;
    private String bandeira;
    private BigDecimal limiteBasico;

}
