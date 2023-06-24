package br.com.carlosjunior.mscartoes.domain.dto;

import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import br.com.carlosjunior.mscartoes.domain.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;


    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limiteBasico);
    }
}
