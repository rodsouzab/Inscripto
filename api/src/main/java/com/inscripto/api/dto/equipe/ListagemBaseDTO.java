package com.inscripto.api.dto.equipe;

public record ListagemBaseDTO(Integer id, String nome, Integer ano, String tema) {
    public ListagemBaseDTO(Integer id, String nome, Integer ano, String tema) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.tema = tema;
    }
}