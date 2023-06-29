package br.com.carlosjunior.msavaliadorcredito.domain.feign;

import br.com.carlosjunior.msavaliadorcredito.domain.feign.dto.CartaoClienteDTOFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesFeign {

    @GetMapping("/{cpf}")
    ResponseEntity<List<CartaoClienteDTOFeign>> findCartoesClienteByCpf(@PathVariable String cpf);

    @GetMapping
    ResponseEntity<List<CartaoClienteDTOFeign>> findCartoesRendaControlada(@RequestParam Long renda);
}
