package com.inscripto.api.domain.encontro;

import java.sql.Date;

public record CadastroEncontroDTO(
        String ano,
        String colegio,
        String tema,
        Date data
) {
}
