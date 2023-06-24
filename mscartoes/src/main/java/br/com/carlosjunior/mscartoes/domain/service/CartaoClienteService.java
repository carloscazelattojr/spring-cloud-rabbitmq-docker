package br.com.carlosjunior.mscartoes.domain.service;

import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private CartaoClienteRepository repository;

    public List<CartaoCliente> findCartoesClienteByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
