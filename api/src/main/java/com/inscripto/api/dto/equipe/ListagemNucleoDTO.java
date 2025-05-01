package com.inscripto.api.dto.equipe;

public record ListagemNucleoDTO(Integer id, String nome, Integer ano) {
    public ListagemNucleoDTO(Integer id, String nome, Integer ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }
}