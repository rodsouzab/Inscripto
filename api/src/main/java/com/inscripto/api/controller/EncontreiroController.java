package com.inscripto.api.controller;

import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.repository.EncontreiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encontreiros")
public class EncontreiroController {

    @Autowired
    private final EncontreiroRepository encontreiroRepository;

    public EncontreiroController(EncontreiroRepository encontreiroRepository) {
        this.encontreiroRepository = encontreiroRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Encontreiro> createEncontreiro(@RequestBody Encontreiro encontreiro) {
        Encontreiro savedEncontreiro = encontreiroRepository.save(encontreiro);
        return ResponseEntity.ok(savedEncontreiro);
    }

    @GetMapping
    public ResponseEntity<List<Encontreiro>> getAllEncontreiros() {
        List<Encontreiro> encontreiros = encontreiroRepository.findAll();
        return ResponseEntity.ok(encontreiros);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Encontreiro> getEncontreiroByCpf(@PathVariable String cpf) {
        Optional<Encontreiro> encontreiro = encontreiroRepository.findById(cpf);
        return encontreiro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<Encontreiro> updateEncontreiro(@PathVariable String cpf, @RequestBody Encontreiro updatedEncontreiro) {
        if (!encontreiroRepository.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        updatedEncontreiro.setCpf(cpf);
        Encontreiro savedEncontreiro = encontreiroRepository.save(updatedEncontreiro);
        return ResponseEntity.ok(savedEncontreiro);
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<Void> deleteEncontreiro(@PathVariable String cpf) {
        if (!encontreiroRepository.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        encontreiroRepository.deleteById(cpf);
        return ResponseEntity.noContent().build();
    }
}
