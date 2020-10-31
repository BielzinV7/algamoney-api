package br.com.gabriel.algamoneyapi.controller;

import br.com.gabriel.algamoneyapi.model.Lancamento;
import br.com.gabriel.algamoneyapi.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController{

    @Autowired
    LancamentoRepository lancamentoRepository;

    @GetMapping
    public List<Lancamento> listar(){

        return lancamentoRepository.findAll();

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){

        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);

        return lancamentoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @PostMapping
    public ResponseEntity<Lancamento> cadatrarLancamento(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder uriBuilder){

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

        URI uri = uriBuilder.path("{/lancamento/{id}").buildAndExpand(lancamento.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(lancamentoSalvo);


    }
}
