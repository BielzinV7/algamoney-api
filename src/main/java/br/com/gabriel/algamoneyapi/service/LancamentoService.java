package br.com.gabriel.algamoneyapi.service;

import br.com.gabriel.algamoneyapi.model.Lancamento;
import br.com.gabriel.algamoneyapi.model.Pessoa;
import br.com.gabriel.algamoneyapi.repository.LancamentoRepository;
import br.com.gabriel.algamoneyapi.repository.PessoaRepository;
import br.com.gabriel.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {

        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if(!pessoaOpt.isPresent() || pessoaOpt.get().isInativa()){

            throw new PessoaInexistenteOuInativaException();

        }

        return lancamentoRepository.save(lancamento);

    }
}
