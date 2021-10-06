package br.com.zupacademy.contadigital.repository;

import br.com.zupacademy.contadigital.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumConta(String numConta);
}
