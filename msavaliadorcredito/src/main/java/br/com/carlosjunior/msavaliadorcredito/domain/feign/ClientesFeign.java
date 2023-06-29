package br.com.carlosjunior.msavaliadorcredito.domain.feign;

import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.DadosClienteDTOFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClientesFeign {

    @GetMapping
    ResponseEntity<DadosClienteDTOFeign> dadosCliente(@RequestParam String cpf);

}
