package com.inscripto.api.controller;

import com.inscripto.api.dto.equipe.*;
import com.inscripto.api.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private final EquipeRepository equipeRepository;

    public EquipeController(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @PostMapping("/bases")
    public void criarBase(@RequestBody CadastroBaseDTO dto) {
        equipeRepository.inserirEquipe(dto.id(), dto.nome(), dto.ano());
        equipeRepository.inserirBase(dto.id(), dto.tema());
    }

    @PostMapping("/nucleos")
    public void criarNucleo(@RequestBody CadastroNucleoDTO dto) {
        equipeRepository.inserirEquipe(dto.id(), dto.nome(), dto.ano());
        equipeRepository.inserirNucleo(dto.id());
    }

    @GetMapping("/bases")
    public ResponseEntity<List<ListagemBaseDTO>> listarBases() {
        return ResponseEntity.ok(equipeRepository.listarBases());
    }

    @GetMapping("/nucleos")
    public ResponseEntity<List<ListagemNucleoDTO>> listarNucleos() {
        return ResponseEntity.ok(equipeRepository.listarNucleos());
    }

    @PutMapping("/bases")
    public void atualizarBase(@RequestBody AtualizacaoBaseDTO dto) {
        equipeRepository.atualizarBase(dto.id(), dto.nome(), dto.ano(), dto.tema());
    }

    @PutMapping("/nucleos")
    public void atualizarNucleo(@RequestBody AtualizacaoNucleoDTO dto) {
        equipeRepository.atualizarNucleo(dto.id(), dto.nome(), dto.ano());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        equipeRepository.deletarPorId(id);
    }
}
