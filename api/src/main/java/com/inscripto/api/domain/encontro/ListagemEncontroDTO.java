package com.inscripto.api.domain.encontro;

public record ListagemEncontroDTO(
        Integer ano,
        String colegio,
        String tema,
        java.sql.Date data
) {
    public ListagemEncontroDTO(Encontro encontro) {
        this(encontro.getAno(), encontro.getColegio(), encontro.getTema(), encontro.getData());
    }
}
