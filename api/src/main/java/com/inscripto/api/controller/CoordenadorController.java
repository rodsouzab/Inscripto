package com.inscripto.api.controller;

import com.inscripto.api.model.Coordenador;
import com.inscripto.api.model.Pessoa;
import com.inscripto.api.repository.CoordenadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.xml.ws.Response;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {

    private final CoordenadorRepository coordenadorRepository;

    @Autowired
    public CoordenadorController(CoordenadorRepository coordenadorRepository) {
        this.coordenadorRepository = coordenadorRepository;
    }

    @PostMapping
    public ResponseEntity<Coordenador> createCoordenador(@RequestBody Coordenador coordenador) {
        Coordenador savedCoordenador = coordenadorRepository.save(coordenador);
        return ResponseEntity.ok(savedCoordenador);
    }

    @GetMapping
    public ResponseEntity<List<Coordenador>> getAllCoordenadores() {
        List<Coordenador> coordenadores = coordenadorRepository.findAll();
        return ResponseEntity.ok(coordenadores);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Coordenador> getCoordenadorByCpf(@PathVariable String cpf) {
        Coordenador coordenador = coordenadorRepository.findByCpf(cpf);
        return (coordenador != null) ? ResponseEntity.ok(coordenador) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarCoordenador(@PathVariable String cpf, @RequestBody Coordenador coordenadorAtualizado) {
        if (!coordenadorRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        coordenadorAtualizado.setCpf(cpf); // garante que o CPF não mude
        coordenadorRepository.atualizarCoordenador(coordenadorAtualizado);
        return ResponseEntity.ok("Coordenador atualizado com sucesso!");
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteCoordenador(@PathVariable String cpf) {
        if (!coordenadorRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        coordenadorRepository.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
