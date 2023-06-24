package br.com.carlosjunior.mscartoes.domain.repository;

import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}
