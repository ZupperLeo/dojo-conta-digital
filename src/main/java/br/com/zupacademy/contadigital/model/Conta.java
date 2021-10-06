package br.com.zupacademy.contadigital.model;

import br.com.zupacademy.contadigital.model.exceptions.ValorSaqueMaiorQueSaldoException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    public void sacar(final BigDecimal valorSaque) {
        if( valorSaque.compareTo(saldo) > 0 )
            throw new ValorSaqueMaiorQueSaldoException(
                    String.format("Valor { %s } maior que saldo { %s }", valorSaque, saldo)
            );
        saldo = saldo.subtract( valorSaque );
    }
}
