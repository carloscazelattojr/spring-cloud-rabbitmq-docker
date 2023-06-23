package br.com.carlosjunior.msclientes.api.controller;


import br.com.carlosjunior.msclientes.domain.dto.ClienteSaveRequest;
import br.com.carlosjunior.msclientes.domain.entity.Cliente;
import br.com.carlosjunior.msclientes.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/status")
    public String status(){
        log.info("Obtendo status cliente");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ClienteSaveRequest request){
        var cliente = request.toModel();
        cliente = service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity dadosCliente(@RequestParam String cpf){
        var cliente = service.getByCpf(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }


}
