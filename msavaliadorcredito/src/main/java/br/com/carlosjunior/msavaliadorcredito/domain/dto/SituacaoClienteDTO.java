package br.com.carlosjunior.msavaliadorcredito.domain.dto;

import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.CartaoClienteDTOFeign;
import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.DadosClienteDTOFeign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoClienteDTO {

    private DadosClienteDTOFeign cliente;
    private List<CartaoClienteDTOFeign> cartoes;

}
