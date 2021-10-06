package br.com.zupacademy.contadigital.controller.dto;

import br.com.zupacademy.contadigital.model.Conta;

public class SaqueResponse {

    private final Conta conta;

    public SaqueResponse(final Conta conta) {
        this.conta = conta;
    }

    public String getNumero() {
        return conta.getNumConta();
    }

    public ClienteDTO getClienteDTO() {
        return new ClienteDTO( conta.getCliente() );
    }

}
