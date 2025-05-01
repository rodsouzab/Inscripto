package com.inscripto.api.controller;

import com.inscripto.api.dto.encontro.*;
import com.inscripto.api.repository.EncontroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Void> criarEncontro(@RequestBody @Valid CadastroEncontroDTO dto) {
        // Verificação: o ano da data deve ser igual ao atributo ano
        if (dto.data().toLocalDate().getYear() != dto.ano()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O ano da data deve ser igual ao atributo ano");
        }

        encontroRepository.inserirEncontro(
            dto.ano(), 
            dto.colegio(), 
            dto.tema(), 
            Date.valueOf(dto.data().toLocalDate())
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public Page<ListagemEncontroDTO> listarEncontros(@PageableDefault(sort = {"ano"}, size = 10) Pageable paginacao) {
        return encontroRepository.listarEncontros(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarEncontro(@RequestBody @Valid AtualizacaoEncontroDTO dto) {
        // Não incluímos a verificação aqui, mas você pode optar por incluir caso também seja necessário na atualização.
        encontroRepository.atualizarEncontro(
            dto.ano(),
            dto.colegio(),
            dto.tema(),
            dto.data() != null ? Date.valueOf(dto.data()) : null
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ano}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable int ano) {
        encontroRepository.deletarPorAno(ano);
        return ResponseEntity.noContent().build();
    }
}
