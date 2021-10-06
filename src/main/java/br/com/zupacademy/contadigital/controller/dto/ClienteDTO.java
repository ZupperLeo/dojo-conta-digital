package br.com.zupacademy.contadigital.controller.dto;

import br.com.zupacademy.contadigital.model.Cliente;

public class ClienteDTO {

    private String nome;
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
