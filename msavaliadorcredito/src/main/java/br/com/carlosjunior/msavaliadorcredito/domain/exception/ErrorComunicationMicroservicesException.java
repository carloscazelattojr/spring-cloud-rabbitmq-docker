package br.com.carlosjunior.msavaliadorcredito.domain.exception;


import lombok.Getter;

public class ErrorComunicationMicroservicesException extends Exception {

    @Getter
    private Integer status;


    public ErrorComunicationMicroservicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
