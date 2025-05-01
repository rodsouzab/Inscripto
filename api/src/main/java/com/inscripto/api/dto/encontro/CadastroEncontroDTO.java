package com.inscripto.api.dto.encontro;

import java.sql.Date;

public record CadastroEncontroDTO(
        Integer ano,
        String colegio,
        String tema,
        Date data
) {
}
