package br.com.gabriel.algamoneyapi.controller;

import br.com.gabriel.algamoneyapi.model.Categoria;
import br.com.gabriel.algamoneyapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){

        return categoriaRepository.findAll();

    }

    @PostMapping
    public ResponseEntity<Categoria>criar(@Valid @RequestBody Categoria categoria, UriComponentsBuilder UriBuilder){

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = UriBuilder.path("/categorias/{codigo}").buildAndExpand(categoria.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(categoriaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long codigo){

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);

        return categoriaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }




}
