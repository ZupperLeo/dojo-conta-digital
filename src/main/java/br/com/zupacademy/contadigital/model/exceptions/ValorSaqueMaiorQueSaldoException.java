package br.com.zupacademy.contadigital.model.exceptions;

public class ValorSaqueMaiorQueSaldoException extends RuntimeException{

    public ValorSaqueMaiorQueSaldoException( final String mensagem ){
        super(mensagem);
    }

}
