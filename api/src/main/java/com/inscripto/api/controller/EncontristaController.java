package com.inscripto.api.controller;

import com.inscripto.api.model.Encontrista;
import com.inscripto.api.repository.EncontristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encontristas")
public class EncontristaController {

    @Autowired
    private final EncontristaRepository encontristaRepository;

    public EncontristaController(EncontristaRepository encontristaRepository) {
        this.encontristaRepository = encontristaRepository;
    }

    @PostMapping
    public Encontrista newEncontrista(@RequestBody Encontrista newEncontrista) {
        return encontristaRepository.save(newEncontrista);
    }

    @GetMapping
    public List<Encontrista> getAllEncontristas() {
        return encontristaRepository.findAll();
    }

    @GetMapping("/{cpf}")
    public Encontrista getEncontristaById(@PathVariable String cpf) {
        return encontristaRepository.findByCpf(cpf);
    }

    @PutMapping("/{cpf}")
    public Encontrista updateEncontrista(@PathVariable String cpf, @RequestBody Encontrista updatedEncontrista) {
        if (!encontristaRepository.existsByCpf(cpf)) {
            throw new RuntimeException("Encontrista não encontrado com CPF: " + cpf);
        }
        updatedEncontrista.setCpf(cpf);
        return encontristaRepository.save(updatedEncontrista);
    }

    @DeleteMapping("/{cpf}")
    public void deleteEncontrista(@PathVariable String cpf) {
        if (encontristaRepository.existsByCpf(cpf)) {
            encontristaRepository.deleteByCpf(cpf);
        }
    }
}
