package br.com.zupacademy.contadigital.controller.dto;

import br.com.zupacademy.contadigital.model.Conta;

public class ContaResponse {

    private String numero;
    private ClienteDTO clienteDTO;

    public ContaResponse(Conta conta) {
        this.numero = conta.getNumConta();
        this.clienteDTO = new ClienteDTO(conta.getCliente());
    }

    public String getNumero() {
        return numero;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }
}
