package com.inscripto.api.controller;

import com.inscripto.api.domain.encontro.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/encontros")
public class EncontroController {

    @Autowired
    private final EncontroRepository encontroRepository;

    public EncontroController(EncontroRepository encontroRepository) {
        this.encontroRepository = encontroRepository;
    }

    @PostMapping
    @Transactional
    public void criarEncontro(@RequestBody @Valid CadastroEncontroDTO dto) {
        encontroRepository.inserirEncontro(dto.ano(), dto.colegio(), dto.tema(), Date.valueOf(dto.data().toLocalDate()));
    }

    @GetMapping
    public Page<ListagemEncontroDTO> listarEncontros(@PageableDefault(sort = {"ano"}, size = 10) Pageable paginacao) {
        return encontroRepository.listarEncontros(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizarEncontro(@RequestBody @Valid AtualizacaoEncontroDTO dto) {
        encontroRepository.atualizarEncontro(String.valueOf(dto.ano()), dto.colegio(), dto.tema(), dto.data() != null ? Date.valueOf(dto.data()) : null);
    }

    @DeleteMapping("/{ano}")
    @Transactional
    public void deletar(@PathVariable String ano) {
        encontroRepository.deletarPorAno(ano);
    }
}
