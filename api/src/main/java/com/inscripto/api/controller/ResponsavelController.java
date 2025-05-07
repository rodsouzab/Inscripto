package com.inscripto.api.controller;

import com.inscripto.api.model.Responsavel;
import com.inscripto.api.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    public Responsavel criarResponsavel(@RequestBody Responsavel newResponsavel) {
        responsavelRepository.criarResponsavel(newResponsavel);
        return newResponsavel;
    }

    @GetMapping
    public List<Responsavel> getAllResponsaveis() {
        return responsavelRepository.listarResponsaveis();
    }

    @DeleteMapping("/{telefone}")
    public void deleteResponsavel(@PathVariable String telefone) {
        int rowsAffected = responsavelRepository.deletarResponsavelPorTelefone(telefone);
        if (rowsAffected == 0) {
            throw new RuntimeException("Responsável com telefone " + telefone + " não encontrado.");
        }
    }

    @PutMapping("/{telefone}")
    public Responsavel updateNomeResponsavel(@PathVariable String telefone, @RequestBody Responsavel updatedResponsavel) {
        int rowsAffected = responsavelRepository.atualizarNomeResponsavel(telefone, updatedResponsavel.getNome());
        if (rowsAffected == 0) {
            throw new RuntimeException("Responsável com telefone " + telefone + " não encontrado.");
        }
        return updatedResponsavel;
    }
}
