package br.com.carlosjunior.mscartoes.domain.service;

import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private CartaoClienteRepository repository;

    public List<CartaoCliente> findCartoesClienteByCpf(String cpf) {
        log.info("[CartaoClienteService].[findCartoesClienteByCpf]");
        List<CartaoCliente> lista = new ArrayList<>();
        try {
            lista = repository.findByCpf(cpf);
        } catch (Exception e) {
            //todo
        }
        return lista;

    }

}
