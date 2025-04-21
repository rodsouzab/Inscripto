package com.inscripto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inscripto.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,String>{
    
}
