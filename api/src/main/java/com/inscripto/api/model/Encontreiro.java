package com.inscripto.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Encontreiro extends Pessoa {

    private boolean fezEjc;
    private Responsavel responsavel;
    private Responsavel responsavelTelefone;

    public boolean isFezEjc() {
        return fezEjc;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public Responsavel getResponsavelTelefone() {
        return responsavelTelefone;
    }
}
