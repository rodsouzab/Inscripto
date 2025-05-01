package com.inscripto.api.dto.encontro;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AtualizacaoEncontroDTO(
        @NotNull Integer ano,
        String colegio,
        String tema,
        LocalDate data
) {
}

