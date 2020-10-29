package br.com.gabriel.algamoneyapi.repository;

import br.com.gabriel.algamoneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<Categoria,Long> {
}
