package br.com.carlosjunior.msavaliadorcredito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoClienteDTO {

    private List<CartaoAprovadoDTO> cartoes;

}
