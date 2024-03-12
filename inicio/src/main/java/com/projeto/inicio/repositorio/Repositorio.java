package com.projeto.inicio.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.inicio.modelo.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa,Integer> {
    List<Pessoa> findAll();
    
    Pessoa findByCodigo(int codigo);
}
