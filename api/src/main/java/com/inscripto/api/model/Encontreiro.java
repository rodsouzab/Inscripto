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
    private String responsavelTelefone;
    private String responsavelNome;

    public boolean isFezEjc() {
        return fezEjc;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public String getResponsavelTelefone() {
        return responsavelTelefone;
    }

    public String getResponsavelNome() {
        return responsavelNome;
    }

    public void setFezEjc(boolean fezEjc) {
        this.fezEjc = fezEjc;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public void setResponsavelTelefone(String responsavelTelefone) {
        this.responsavelTelefone = responsavelTelefone;
    }
    public void setResponsavelNome(String responsavelNome) {
        this.responsavelNome = responsavelNome;
    }
}
