package com.inscripto.api.controller;

import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.repository.EncontreiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encontreiros")
public class EncontreiroController {

    private final EncontreiroRepository encontreiroRepository;

    @Autowired
    public EncontreiroController(EncontreiroRepository encontreiroRepository) {
        this.encontreiroRepository = encontreiroRepository;
    }

    @PostMapping
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
        Encontreiro encontreiro = encontreiroRepository.findByCpf(cpf);
        return (encontreiro != null) ? ResponseEntity.ok(encontreiro) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarEncontreiro(@PathVariable String cpf, @RequestBody Encontreiro encontreiroAtualizado) {
        if (!encontreiroRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        encontreiroAtualizado.setCpf(cpf); // garante que o CPF não mude
        encontreiroRepository.atualizarEncontreiro(encontreiroAtualizado);
        return ResponseEntity.ok("Encontreiro atualizado com sucesso!");
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEncontreiro(@PathVariable String cpf) {
        if (!encontreiroRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        encontreiroRepository.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
