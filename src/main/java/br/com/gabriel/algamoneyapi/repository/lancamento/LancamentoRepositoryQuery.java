package br.com.gabriel.algamoneyapi.repository.lancamento;

import br.com.gabriel.algamoneyapi.model.Lancamento;
import br.com.gabriel.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
