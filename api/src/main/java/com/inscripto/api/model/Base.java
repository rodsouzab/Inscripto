package com.inscripto.api.model;

import com.inscripto.api.dto.equipe.CadastroBaseDTO;
import com.inscripto.api.dto.equipe.CadastroEquipeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Base extends Equipe {
    private String tema;

    public Base(CadastroBaseDTO dto) {
        super(new CadastroEquipeDTO(dto.id(), dto.nome(), dto.ano()));
        this.tema = dto.tema();
    }
}