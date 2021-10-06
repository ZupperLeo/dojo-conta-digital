package br.com.zupacademy.contadigital.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ContaRequest {

    @NotBlank
    private String numConta;
    @NotBlank
    private String clientId;
    @NotNull
    private Double saldo;

    @Deprecated
    public ContaRequest() {
    }

    public String getClientId() {
        return clientId;
    }

    public ContaRequest(@NotBlank String numConta, @NotNull String clientId, @NotNull @Positive Double saldo) {
        this.numConta = numConta;
        this.clientId = clientId;
        this.saldo = saldo;
    }

}
