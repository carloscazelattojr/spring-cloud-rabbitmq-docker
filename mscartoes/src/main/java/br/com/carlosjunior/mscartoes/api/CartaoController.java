package br.com.carlosjunior.mscartoes.api;

import br.com.carlosjunior.mscartoes.domain.dto.CartaoSaveRequest;
import br.com.carlosjunior.mscartoes.domain.dto.CartoesPorClienteResponse;
import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import br.com.carlosjunior.mscartoes.domain.service.CartaoClienteService;
import br.com.carlosjunior.mscartoes.domain.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoController {


    private final CartaoService cartaoService;
    private final CartaoClienteService cartaoClienteService;

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody CartaoSaveRequest request) {
        cartaoService.save(request.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> findCartoesRendaControlada(@RequestParam Long renda) {
        return ResponseEntity.ok(cartaoService.findCartoesRendaMenorIgual(renda));
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<List<CartoesPorClienteResponse>> findCartoesClienteByCpf(@PathVariable String cpf) {
        List<CartaoCliente> lista = cartaoClienteService.findCartoesClienteByCpf(cpf);
        List<CartoesPorClienteResponse> novaLista = lista.stream()
                .map((CartoesPorClienteResponse::fromModel))
                .collect(Collectors.toList());
        return ResponseEntity.ok(novaLista);
    }

}
