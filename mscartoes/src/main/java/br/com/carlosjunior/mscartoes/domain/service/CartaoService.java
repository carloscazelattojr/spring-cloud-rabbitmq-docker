package br.com.carlosjunior.mscartoes.domain.service;

import br.com.carlosjunior.mscartoes.domain.entitie.Cartao;
import br.com.carlosjunior.mscartoes.domain.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao){
        log.info("[CartaoService].[save]");
        return repository.save(cartao);    }

    public List<Cartao> findCartoesRendaMenorIgual( Long renda){
        log.info("[CartaoService].[findCartoesRendaMenorIgual]");
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

}
