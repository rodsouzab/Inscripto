package com.inscripto.api.controller;

import com.inscripto.api.dto.equipe.*;
import com.inscripto.api.repository.EquipeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private final EquipeRepository equipeRepository;

    public EquipeController(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @PostMapping("/bases")
    @Transactional
    public void criarBase(@RequestBody @Valid CadastroBaseDTO dto) {
        equipeRepository.inserirEquipe(dto.id(), dto.nome(), dto.ano());
        equipeRepository.inserirBase(dto.id(), dto.tema());
    }

    @PostMapping("/nucleos")
    @Transactional
    public void criarNucleo(@RequestBody @Valid CadastroNucleoDTO dto) {
        equipeRepository.inserirEquipe(dto.id(), dto.nome(), dto.ano());
        equipeRepository.inserirNucleo(dto.id());
    }

    @GetMapping("/bases")
    public Page<ListagemBaseDTO> listarBases(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao) {
        return equipeRepository.listarBases(paginacao);
    }

    @GetMapping("/nucleos")
    public Page<ListagemNucleoDTO> listarNucleos(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao) {
        return equipeRepository.listarNucleos(paginacao);
    }

    @PutMapping("/bases")
    @Transactional
    public void atualizarBase(@RequestBody @Valid AtualizacaoBaseDTO dto) {
        equipeRepository.atualizarBase(dto.id(), dto.nome(), dto.ano(), dto.tema());
    }

    @PutMapping("/nucleos")
    @Transactional
    public void atualizarNucleo(@RequestBody @Valid AtualizacaoNucleoDTO dto) {
        equipeRepository.atualizarNucleo(dto.id(), dto.nome(), dto.ano());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Integer id) {
        equipeRepository.deletarPorId(id);
    }
}
