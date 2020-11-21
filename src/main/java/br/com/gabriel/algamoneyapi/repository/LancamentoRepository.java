package br.com.gabriel.algamoneyapi.repository;

import br.com.gabriel.algamoneyapi.model.Lancamento;
import br.com.gabriel.algamoneyapi.repository.filter.LancamentoFilter;
import br.com.gabriel.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long> , LancamentoRepositoryQuery{


}
