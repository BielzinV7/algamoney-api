package br.com.gabriel.algamoneyapi.controller;

import br.com.gabriel.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler;
import br.com.gabriel.algamoneyapi.model.Lancamento;
import br.com.gabriel.algamoneyapi.repository.LancamentoRepository;
import br.com.gabriel.algamoneyapi.repository.filter.LancamentoFilter;
import br.com.gabriel.algamoneyapi.service.LancamentoService;
import br.com.gabriel.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController{

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    MessageSource messageSource;

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){

        return lancamentoRepository.filtrar(lancamentoFilter, pageable);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo){

        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);

        return lancamentoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @PostMapping
    public ResponseEntity<Lancamento> cadatrarLancamento(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder uriBuilder){

        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

        URI uri = uriBuilder.path("{/lancamento/{id}").buildAndExpand(lancamento.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(lancamentoSalvo);


    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLancamento(@PathVariable long codigo){

        lancamentoRepository.deleteById(codigo);

    }

    @ExceptionHandler(PessoaInexistenteOuInativaException.class)
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){

        String mensagemUsuairo = messageSource.getMessage("pessoa.inexistente-ou-inativa",null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuairo,mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }


}
