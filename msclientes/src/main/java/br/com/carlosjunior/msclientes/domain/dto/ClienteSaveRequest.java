package br.com.carlosjunior.msclientes.domain.dto;

import br.com.carlosjunior.msclientes.domain.entity.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(nome, cpf, idade);
    }

}
