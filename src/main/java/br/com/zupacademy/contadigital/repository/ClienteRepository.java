package br.com.zupacademy.contadigital.repository;

import br.com.zupacademy.contadigital.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
