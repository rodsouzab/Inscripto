package com.inscripto.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="responsavel")
public class Responsavel {
    
    @Id
    private String telefone_responsavel;

    private String nome_responsavel;


    public String getTelefone_responsavel() {
        return telefone_responsavel;
    }

    public void setTelefone_responsavel(String telefone_responsavel) {
        this.telefone_responsavel = telefone_responsavel;
    }

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }
}
