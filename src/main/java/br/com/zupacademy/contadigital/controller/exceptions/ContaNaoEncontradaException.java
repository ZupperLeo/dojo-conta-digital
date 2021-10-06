package br.com.zupacademy.contadigital.controller.exceptions;

public class ContaNaoEncontradaException extends RuntimeException{

    public ContaNaoEncontradaException( final String mensagem ){
        super(mensagem);
    }

}
