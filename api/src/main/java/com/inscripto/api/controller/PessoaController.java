package com.inscripto.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inscripto.api.model.Pessoa;
import com.inscripto.api.repository.PessoaRepository;

@RestController
public class PessoaController {
    
    @Autowired
    private PessoaRepository pr;

    @PostMapping("/pessoa")
    Pessoa novaPessoa(@RequestBody Pessoa novaPessoa){
        return pr.save(novaPessoa);
    }

    @GetMapping("/pessoas")
    List<Pessoa> getTodasPessoas() {
    return pr.findAll();
}

    @PostMapping("/login")
    public String login(@RequestBody Pessoa pessoa){
        Optional<Pessoa> pessoaExiste = pr.findById(pessoa.getCpf());

        if(!pessoaExiste.isPresent()){
            return "CPF não cadastrado!";
        }

        Pessoa usuario = pessoaExiste.get();

        if(!usuario.getSenha().equals(pessoa.getSenha())){
            return "Senha incorreta!";
        }

        return "Login bem-sucedido!";

    }


}
