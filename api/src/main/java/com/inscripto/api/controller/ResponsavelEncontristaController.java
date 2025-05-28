package com.inscripto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inscripto.api.dto.VinculoResponsavelEncontristaDTO;
import com.inscripto.api.repository.EncontristaRepository;

@RestController
@RequestMapping("/responsavel_encontrista")
public class ResponsavelEncontristaController {

    @Autowired
    private EncontristaRepository encontristaRepository;

    @PostMapping("/vincular")
    public void vincularResponsaveisAEncontrista(@RequestBody List<VinculoResponsavelEncontristaDTO> dtos) {
        dtos.forEach(dto -> 
            encontristaRepository.criarRelacionamentoResponsavelEncontrista(dto.telefoneResponsavel(), dto.cpfEncontrista())
        );
    }
}

