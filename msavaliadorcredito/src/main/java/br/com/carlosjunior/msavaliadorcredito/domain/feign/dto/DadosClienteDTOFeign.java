package br.com.carlosjunior.msavaliadorcredito.domain.feign.dto;

import lombok.Data;

@Data
public class DadosClienteDTOFeign {

    private String nome;
    private String cpf;
    private Integer idade;
}
