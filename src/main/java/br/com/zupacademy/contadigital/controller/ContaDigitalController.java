package br.com.zupacademy.contadigital.controller;

import br.com.zupacademy.contadigital.controller.dto.ContaResponse;
import br.com.zupacademy.contadigital.controller.dto.DepositaRequest;
import br.com.zupacademy.contadigital.model.Conta;
import br.com.zupacademy.contadigital.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaDigitalController {

    @Autowired
    private ContaRepository contaRepository;

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
}
