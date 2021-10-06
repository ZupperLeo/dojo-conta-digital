package br.com.zupacademy.contadigital.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class DepositaRequest {

    private @NotBlank String numConta;
    private @NotNull @Positive BigDecimal valorDeposito;

    @Deprecated
    public DepositaRequest() {
    }

    public DepositaRequest(@NotBlank String numConta, @NotNull @Positive BigDecimal valorDeposito) {
        this.numConta = numConta;
        this.valorDeposito = valorDeposito;
    }

    public String getNumConta() {
        return numConta;
    }

    public BigDecimal getValorDeposito() {
        return valorDeposito;
    }
}
