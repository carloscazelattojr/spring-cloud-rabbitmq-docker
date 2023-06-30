package br.com.carlosjunior.msavaliadorcredito.domain.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException {
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
