package br.com.carlosjunior.mscartoes.domain.service;

import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao){
        return repository.save(cartao);
    }

    public List<Cartao> findCartoesRendaMenorIgual( Long renda){
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

}
