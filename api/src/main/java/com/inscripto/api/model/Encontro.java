package com.inscripto.api.model;

import com.inscripto.api.dto.encontro.CadastroEncontroDTO;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@EqualsAndHashCode(of = "ano")
@NoArgsConstructor
@AllArgsConstructor
public class Encontro {

    private Integer ano;

    private String colegio;

    private String tema;

    private Date data;


    public Encontro(int ano) {
        this.ano = ano;

    }

    public Integer getAno() {
        return ano;
    }
}
