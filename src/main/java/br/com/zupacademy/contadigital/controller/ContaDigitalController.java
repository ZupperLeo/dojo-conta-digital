package br.com.zupacademy.contadigital.controller;

import br.com.zupacademy.contadigital.controller.dto.ContaResponse;
import br.com.zupacademy.contadigital.controller.dto.DepositaRequest;
import br.com.zupacademy.contadigital.controller.dto.SaqueRequest;
import br.com.zupacademy.contadigital.controller.dto.SaqueResponse;
import br.com.zupacademy.contadigital.controller.exceptions.ClienteNaoFoiEncontradoException;
import br.com.zupacademy.contadigital.controller.exceptions.ContaNaoEncontradaException;
import br.com.zupacademy.contadigital.model.Cliente;
import br.com.zupacademy.contadigital.model.Conta;
import br.com.zupacademy.contadigital.repository.ClienteRepository;
import br.com.zupacademy.contadigital.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaDigitalController {

    @Autowired
    private ContaRepository contaRepository;

    private final ClienteRepository clienteRepository;

    public ContaDigitalController(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/depositar")
    public ResponseEntity<?> depositar(@Valid @RequestBody DepositaRequest depositaRequest) {
        Optional<Conta> buscaConta = contaRepository.findByNumConta(depositaRequest.getNumConta());

        if (buscaConta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Conta conta = buscaConta.get();
        conta.depositar(depositaRequest.getValorDeposito());

        contaRepository.save(conta);

        ContaResponse contaResponse = new ContaResponse(conta);

        return ResponseEntity.ok().body(contaResponse);
    }

    @PostMapping("/sacar")
    @ResponseStatus( HttpStatus.OK )
    SaqueResponse sacar(@Valid @RequestBody SaqueRequest saqueRequest ){

        final Optional<Cliente> byId = clienteRepository.findById(saqueRequest.getIdCliente());

        if( byId.isEmpty() ) throw new ClienteNaoFoiEncontradoException(
                String.format("Cliente com id { %d } não foi encontrado.", saqueRequest.getIdCliente())
        );

        final Optional<Conta> byNumConta = contaRepository.findByNumConta(saqueRequest.getNumConta());

        if( byNumConta.isEmpty() ) throw new ContaNaoEncontradaException(
                String.format("Conta com numero { %s } não foi encontrado.", saqueRequest.getNumConta() )
        );

        final Conta conta = byNumConta.get();

        conta.sacar( saqueRequest.getValorSaque() );

        contaRepository.save( conta );

        return new SaqueResponse( conta );

    }

}
