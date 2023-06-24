package br.com.carlosjunior.mscartoes.domain.entitie;

import br.com.carlosjunior.mscartoes.domain.enums.BandeiraCartao;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nome;
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome,
                  BandeiraCartao bandeira,
                  BigDecimal renda,
                  BigDecimal limiteBasico) {
        Nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
