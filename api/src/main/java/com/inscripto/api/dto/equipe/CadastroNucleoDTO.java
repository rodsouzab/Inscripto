package com.inscripto.api.dto.equipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroNucleoDTO(

        @NotNull
        Integer id,

        @NotBlank
        String nome,

        @NotNull
        Integer ano

) {
}
