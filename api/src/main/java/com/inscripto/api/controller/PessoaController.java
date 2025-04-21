package com.inscripto.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inscripto.api.model.Pessoa;
import com.inscripto.api.repository.PessoaRepository;

@RestController
public class PessoaController {
    
    @Autowired
    private PessoaRepository pr;

    @PostMapping("/pessoa")
    public String novaPessoa(@RequestBody Pessoa novaPessoa) {
    if (pr.existsById(novaPessoa.getCpf())) {
        return "Já existe uma pessoa cadastrada com esse CPF!";
    }
    pr.save(novaPessoa);
    return "Pessoa cadastrada com sucesso!";
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

    @PostMapping("/admin-login")
    public String loginAdmin(@RequestBody Pessoa pessoa){
    Optional<Pessoa> pessoaExiste = pr.findById(pessoa.getCpf());

    if (!pessoaExiste.isPresent()) {
        return "CPF não cadastrado!";
    }

    Pessoa usuario = pessoaExiste.get();

    if (!usuario.isAdmin()) {
        return "Usuário sem permissão";
    }

    if (!usuario.getSenha().equals(pessoa.getSenha())) {
        return "Senha incorreta!";
    }

    return "Login bem-sucedido";
}
}
