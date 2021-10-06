package br.com.zupacademy.contadigital.controller.dto;

import br.com.zupacademy.contadigital.model.Conta;

public class ContaResponse {

    private String numero;
    private ClienteDTO cliente;

    public ContaResponse(Conta conta) {
        this.numero = conta.getNumConta();
        this.cliente = new ClienteDTO(conta.getCliente());
    }

    public String getNumero() {
        return numero;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }
}
