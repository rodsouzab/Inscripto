package com.inscripto.api.controller;

import com.inscripto.api.dto.encontro.*;
import com.inscripto.api.repository.EncontroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/encontros")
public class EncontroController {

    @Autowired
    private final EncontroRepository encontroRepository;

    public EncontroController(EncontroRepository encontroRepository) {
        this.encontroRepository = encontroRepository;
    }

    @PostMapping
    public ResponseEntity<Void> criarEncontro(@RequestBody CadastroEncontroDTO dto) {
        encontroRepository.inserirEncontro(
                dto.ano(),
                dto.colegio(),
                dto.tema(),
                Date.valueOf(dto.data().toLocalDate())
        );
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<ListagemEncontroDTO>> listarEncontros() {
        return ResponseEntity.ok(encontroRepository.listarEncontros());
    }

    @PutMapping
    public ResponseEntity<Void> atualizarEncontro(@RequestBody AtualizacaoEncontroDTO dto) {
        encontroRepository.atualizarEncontro(
                dto.ano(),
                dto.colegio(),
                dto.tema(),
                dto.data() != null ? Date.valueOf(dto.data()) : null
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ano}")
    public ResponseEntity<Void> deletar(@PathVariable int ano) {
        encontroRepository.deletarPorAno(ano);
        return ResponseEntity.noContent().build();
    }
}
