package com.inscripto.api.dto.equipe;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoBaseDTO(
        @NotNull
        Integer id,

        String nome,

        Integer ano,

        String tema
) {
}
