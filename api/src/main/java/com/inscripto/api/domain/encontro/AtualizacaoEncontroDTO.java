package com.inscripto.api.domain.encontro;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AtualizacaoEncontroDTO(
        @NotNull Integer ano,
        String colegio,
        String tema,
        LocalDate data
) {
}

