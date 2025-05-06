package com.inscripto.api.controller;

import com.inscripto.api.model.Encontrista;
import com.inscripto.api.repository.EncontristaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encontristas")
public class EncontristaController {

    @Autowired
    private final EncontristaRepository encontristaRepository;

    public EncontristaController(EncontristaRepository encontristaRepository) {
        this.encontristaRepository = encontristaRepository;
    }

    @PostMapping
    public Encontrista newEncontrista(@RequestBody @Valid Encontrista newEncontrista) {
        return encontristaRepository.save(newEncontrista);
    }

    @GetMapping
    public List<Encontrista> getAllEncontristas() {
        return encontristaRepository.findAll();
    }

    @GetMapping("/{cpf}")
    public Optional<Encontrista> getEncontristaById(@PathVariable String cpf) {
        return encontristaRepository.findById(cpf);
    }

    @PutMapping("/{cpf}")
    public Encontrista updateEncontrista(@PathVariable String cpf, @RequestBody @Valid Encontrista updatedEncontrista) {
        return encontristaRepository.findById(cpf)
                .map(encontrista -> {
                    // Atualize os campos necessários
                    encontrista.setPaisSeparados(updatedEncontrista.isPaisSeparados());
                    encontrista.setEncontro(updatedEncontrista.getEncontro());
                    encontrista.setNucleo(updatedEncontrista.getNucleo());
                    return encontristaRepository.save(encontrista);
                })
                .orElseThrow(() -> new RuntimeException("Encontrista não encontrado com CPF: " + cpf));
    }

    @DeleteMapping("/{cpf}")
    public void deleteEncontrista(@PathVariable String cpf) {
        encontristaRepository.deleteById(cpf);
    }
}
