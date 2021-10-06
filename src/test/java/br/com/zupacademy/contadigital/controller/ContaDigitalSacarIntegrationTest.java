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
public class ContaDigitalSacarIntegrationTest {

    private static final String ENDPOINT = "/contas/sacar";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Saque realizado com sucesso")
    void saqueRealizadoComSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"idCliente\": \"1\", \"numConta\": \"11111111111\", \"valorSaque\": 100 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.numero").value("11111111111") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.cliente.nome").value("Joao") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.cliente.email").value("joao@email.com"));
    }

    @Test
    @DisplayName("Cliente nao encontrado")
    void clienteNaoEncontrado() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"idCliente\": \"50\", \"numConta\": \"11111111111\", \"valorSaque\": 100 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isNotFound() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.field").value("idCliente") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.message").value("Cliente com id { 50 } não foi encontrado.") );
    }

    @Test
    @DisplayName("Conta nao encontrada")
    void contaNaoEncontrada() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"idCliente\": \"1\", \"numConta\": \"99999999999\", \"valorSaque\": 100 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isNotFound() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.field").value("numConta") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.message").value("Conta com numero { 99999999999 } não foi encontrado.") );
    }

    @Test
    @DisplayName("Sacando valor maior que saldo")
    void sacandoValorMaiorQueSaldo() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"idCliente\": \"1\", \"numConta\": \"11111111111\", \"valorSaque\": 600 }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isUnprocessableEntity() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.field").value("valorSaque") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.message").value("Valor { 600 } maior que saldo { 400.00 }") );
    }

    @Test
    @DisplayName("Valor saque nulo")
    void valorSaqueNulo() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content("{ \"idCliente\": \"1\", \"numConta\": \"11111111111\" }")
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isBadRequest() );
    }

}
