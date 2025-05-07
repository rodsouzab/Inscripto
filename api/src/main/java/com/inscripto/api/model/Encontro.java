package com.inscripto.api.model;

import com.inscripto.api.dto.encontro.CadastroEncontroDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@EqualsAndHashCode(of = "ano")
@NoArgsConstructor
@AllArgsConstructor
public class Encontro {

    private Integer ano;

    private String colegio;

    private String tema;

    private Date data;

    public Encontro(CadastroEncontroDTO dto) {
        this.ano = dto.ano();
        this.colegio = dto.colegio();
        this.tema = dto.tema();
        this.data = dto.data();
    }
}
