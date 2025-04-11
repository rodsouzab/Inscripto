package com.inscripto.api.domain.equipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoBaseDTO(
        @NotNull
        Integer id,

        String nome,

        Integer ano,

        String tema
) {
}
