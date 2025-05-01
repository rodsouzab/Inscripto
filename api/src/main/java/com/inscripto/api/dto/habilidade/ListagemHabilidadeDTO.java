package com.inscripto.api.dto.habilidade;

public record ListagemHabilidadeDTO(Integer id, String habilidade) {
    public ListagemHabilidadeDTO(Integer id, String habilidade) {
        this.id = id;
        this.habilidade = habilidade;
    }
}
