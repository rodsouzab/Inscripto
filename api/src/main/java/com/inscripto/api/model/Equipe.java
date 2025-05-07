package com.inscripto.api.model;

import com.inscripto.api.dto.equipe.CadastroBaseDTO;
import com.inscripto.api.dto.equipe.CadastroEquipeDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor

public class Equipe {

    private Integer id;

    private String nome;

    private Integer ano;

    public Equipe(CadastroEquipeDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.ano = dto.ano();
    }

}
