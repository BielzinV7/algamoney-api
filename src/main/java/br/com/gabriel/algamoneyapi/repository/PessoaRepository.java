package br.com.gabriel.algamoneyapi.repository;

import br.com.gabriel.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
