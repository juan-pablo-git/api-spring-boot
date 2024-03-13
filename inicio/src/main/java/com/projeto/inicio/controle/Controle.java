package com.projeto.inicio.controle;

import org.springframework.web.bind.annotation.RestController;

import com.projeto.inicio.modelo.Pessoa;
import com.projeto.inicio.repositorio.Repositorio;
import com.projeto.inicio.servico.Servico;

import java.util.List;

import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private Servico servico;


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
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    @GetMapping("/api/idademaiorigual")
    public List<Pessoa> idadeMaiorIgual() {
        return acao.idadeMaiorIgual(18);
    }
    

    @GetMapping("/api/somaidades")
    public int somaIdades() {
        return acao.somaIdades();
    }
    

    @GetMapping("/api/iniciacom")
    public List<Pessoa> iniciaCom() {
        return acao.findByNomeStartsWith("o");
    }

    @GetMapping("/api/finalizacom")
    public List<Pessoa> finalizacom() {
        return acao.findByNomeEndsWith("a");
    }
    
    

    @GetMapping("/api/ordenarnome2")
    public List<Pessoa> ordenarnome2() {
        return acao.findByNomeOrderByIdadeDesc("pablo");
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

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }
    

}
