package br.com.gabriel.algamoneyapi.controller;

import br.com.gabriel.algamoneyapi.model.Pessoa;
import br.com.gabriel.algamoneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController{

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder){

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = uriBuilder.path("/pessoa/{codigo}").buildAndExpand(pessoa.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(pessoaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloId(@PathVariable Long codigo){

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);

        return pessoaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){

        pessoaRepository.deleteById(codigo);
    }

}
