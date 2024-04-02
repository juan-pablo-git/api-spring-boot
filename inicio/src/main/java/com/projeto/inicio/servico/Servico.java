package com.projeto.inicio.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.inicio.modelo.Mensagem;
import com.projeto.inicio.modelo.Pessoa;
import com.projeto.inicio.repositorio.Repositorio;

@Service
public class Servico {
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Cadastrar Pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchhido");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else if (obj.getIdade() < 18) {
            mensagem.setMensagem("Idade não pode ser menor que 18");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            mensagem.setMensagem("Registro criado com sucesso");
            acao.save(obj);
            return new ResponseEntity<>(mensagem,HttpStatus.CREATED);
        }
    }

    //Selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        Pessoa p = acao.findByCodigo(codigo);
        if(p == null){
            p = new Pessoa();
            p.setCodigo(0);
            p.setNome("Pesoa não encontrada");
            mensagem.setMensagem("Pessoa não encontrada");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST); 
        }else{

            return new ResponseEntity<>(p,HttpStatus.ACCEPTED); 
        }
    }

    // Metodo para editar dados
    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("o codugo informado não existe");
            return new ResponseEntity<>(mensagem,HttpStatus.NOT_FOUND);
        }else if(obj.getNome().equals("")){
            mensagem.setMensagem("É nessesario informar o nome");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade() < 0){
            mensagem.setMensagem("Informe a mensagem valida");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj),HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int codigo ){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Registro não existe");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);
            mensagem.setMensagem("Registro deletado com sucesso");
            return new ResponseEntity<>(mensagem,HttpStatus.OK);
        }
    }

}
