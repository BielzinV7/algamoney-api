package br.com.gabriel.algamoneyapi.service;

import br.com.gabriel.algamoneyapi.model.Pessoa;
import br.com.gabriel.algamoneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo,Pessoa pessoa){

        Pessoa pessoaSalva = buscarPessoa(codigo);
        BeanUtils.copyProperties(pessoa,pessoaSalva,"codigo");
        return pessoaRepository.save(pessoaSalva);

    }


    public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoa(codigo);
        pessoaSalva.setAtivo(ativo);
        return pessoaRepository.save(pessoaSalva);

    }

    //metodo de buscar pessoa pra ser reaproveitado nas regras de negócio
    public Pessoa buscarPessoa(Long codigo){

        Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return pessoaSalva;

    }
}
