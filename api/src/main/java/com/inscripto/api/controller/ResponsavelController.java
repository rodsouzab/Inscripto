package com.inscripto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.inscripto.api.repository.ResponsavelRepository;
import com.inscripto.api.model.Responsavel;
import java.util.List;

@RestController
public class ResponsavelController {
    
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping("/responsavel")
    Responsavel newResponsavel(@RequestBody Responsavel newResponsavel) {
        return responsavelRepository.save(newResponsavel);
    }

    @GetMapping("/responsavel")
    public List<Responsavel> getAllResponsaveis() {
        return responsavelRepository.findAll();
    }

    @DeleteMapping("/responsavel/{telefone}")
        public void deleteResponsavel(@PathVariable String telefone) {
         if (responsavelRepository.existsById(telefone)) {
            responsavelRepository.deleteById(telefone);
         } else {
            throw new RuntimeException("Responsável com telefone " + telefone + " não encontrado.");
        }
    }

    @PutMapping("/responsavel/{telefone}")
    public Responsavel updateNomeResponsavel(@PathVariable String telefone, @RequestBody Responsavel updatedResponsavel) {
    return responsavelRepository.findById(telefone)
        .map(responsavel -> {
            responsavel.setNome(updatedResponsavel.getNome());
            return responsavelRepository.save(responsavel);
        })
        .orElseThrow(() -> new RuntimeException("Responsável com telefone " + telefone + " não encontrado."));
    }

}
