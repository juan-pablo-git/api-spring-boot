package com.projeto.inicio.controle;

import org.springframework.web.bind.annotation.RestController;

import com.projeto.inicio.modelo.Pessoa;
import com.projeto.inicio.repositorio.Repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;


    @PutMapping("/api")
    public Pessoa editar(@RequestBody Pessoa obj) {

        return acao.save(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public void remover(@PathVariable int codigo) {
        Pessoa obj = selecionarPeloCodigo(codigo);
        acao.delete(obj);
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }


    @PostMapping("/api")
    public Pessoa cadastrar(@RequestBody Pessoa obj) {
        return acao.save(obj);
    }

    @GetMapping("/api/ordenarnomes")
    public List<Pessoa> getMethodName() {
        return acao.findByOrderByNome();
    }
    

    @GetMapping("/selecionar")
    public List<Pessoa> selecionar() {
        return acao.findAll();
    }

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    @GetMapping("/api/{codigo}")
    public Pessoa selecionarPeloCodigo(@PathVariable int codigo) {
        return acao.findByCodigo(codigo);
    }

    @GetMapping("")
    public String mensagem() {

        return "Hello Word";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome) {
        return "Boas Vindas " + nome;
    }

}
