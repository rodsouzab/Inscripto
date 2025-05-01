package com.inscripto.api.dto.equipe;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoNucleoDTO(

        @NotNull
        Integer id,

        String nome,

        Integer ano
) {
}
