package br.com.zupacademy.contadigital.controller.exceptions;

public class ClienteNaoFoiEncontradoException extends RuntimeException {

    public ClienteNaoFoiEncontradoException(final String mensagem){
        super( mensagem );
    }

}
