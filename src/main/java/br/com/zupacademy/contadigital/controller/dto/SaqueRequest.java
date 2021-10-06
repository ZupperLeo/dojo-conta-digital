package br.com.zupacademy.contadigital.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class SaqueRequest {

    private @NotNull Long idCliente;

    private @NotBlank String numConta;

    private @NotNull @Positive BigDecimal valorSaque;

    @Deprecated
    public SaqueRequest(){}

    public SaqueRequest(final Long idCliente, final String numConta, final BigDecimal valorSaque) {
        this.idCliente = idCliente;
        this.numConta = numConta;
        this.valorSaque = valorSaque;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNumConta() {
        return numConta;
    }

    public BigDecimal getValorSaque() {
        return valorSaque;
    }

}
