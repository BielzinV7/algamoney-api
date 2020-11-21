package br.com.gabriel.algamoneyapi.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LancamentoFilter {

    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate DataVencimentoDe;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate DataVencimentoAte;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimentoDe() {
        return DataVencimentoDe;
    }

    public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
        this.DataVencimentoDe = dataVencimentoDe;
    }

    public LocalDate getDataVencimentoAte() {
        return DataVencimentoAte;
    }

    public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
        this.DataVencimentoAte = dataVencimentoAte;
    }
}
