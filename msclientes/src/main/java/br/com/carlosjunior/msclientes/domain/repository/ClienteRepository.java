package br.com.carlosjunior.msclientes.domain.repository;

import br.com.carlosjunior.msclientes.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findByCpf(String cpf);
}
