package br.com.zupacademy.contadigital.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String numConta;
    @ManyToOne
    private @NotNull Cliente cliente;
    private @NotNull BigDecimal saldo = BigDecimal.ZERO;

    @Deprecated
    public Conta() {
    }

    public Conta(@NotBlank String numConta, @NotNull Cliente cliente) {
        this.numConta = numConta;
        this.cliente = cliente;
    }

    public String getNumConta() {
        return numConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
        }
    }
}
