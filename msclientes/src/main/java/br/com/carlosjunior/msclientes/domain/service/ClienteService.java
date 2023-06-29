package br.com.carlosjunior.msclientes.domain.service;

import br.com.carlosjunior.msclientes.domain.entity.Cliente;
import br.com.carlosjunior.msclientes.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente){
        log.info("[ClienteService].[save]");
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf){
        log.info("[ClienteService].[getByCpf]");
        return repository.findByCpf(cpf);
    }

}
