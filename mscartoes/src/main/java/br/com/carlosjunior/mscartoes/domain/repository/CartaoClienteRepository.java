package br.com.carlosjunior.mscartoes.domain.repository;

import br.com.carlosjunior.mscartoes.domain.entitie.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, Long> {

    List<CartaoCliente> findByCpf(String cpf);
}
