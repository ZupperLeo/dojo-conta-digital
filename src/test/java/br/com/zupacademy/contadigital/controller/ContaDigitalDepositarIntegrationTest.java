package br.com.zupacademy.contadigital.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles( value = "test" )
@SpringBootTest
@AutoConfigureMockMvc
public class ContaDigitalDepositarIntegrationTest {

    private static final String ENDPOINT = "/contas/depositar";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deposito realizado com sucesso")
    void depositoRealizadoComSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"numConta\": \"11111111111\", \"valorDeposito\": 100 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.numero").value("11111111111") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.cliente.nome").value("Joao") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.cliente.email").value("joao@email.com"));
    }

    @Test
    @DisplayName("Conta nao encontrada")
    void contaNaoEncontrada() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"numConta\": \"99999999999\", \"valorDeposito\": 100 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    @Test
    @DisplayName("valor deposito nulo")
    void valorDepositoNulo() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"numConta\": \"99999999999\" }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isBadRequest() );
    }

}
