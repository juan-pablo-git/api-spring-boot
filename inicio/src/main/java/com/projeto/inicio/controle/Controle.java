package com.projeto.inicio.controle;

import org.springframework.web.bind.annotation.RestController;

import com.projeto.inicio.modelo.Pessoa;
import com.projeto.inicio.repositorio.Repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @PostMapping("/api")    
    public Pessoa cadastrar(@RequestBody Pessoa obj){
        return acao.save(obj);
    }

    @GetMapping("/selecionar")
    public List<Pessoa> selecionar(){
        return acao.findAll();
    }

    @GetMapping("/api/{codigo}")
    public Pessoa selecionarPeloCodigo(@PathVariable int codigo){
        return acao.findByCodigo(codigo);
    }

    @GetMapping("")
    public String mensagem(){

        return "Hello Word";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas( @PathVariable String nome){
        return "Boas Vindas "+ nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }


}
