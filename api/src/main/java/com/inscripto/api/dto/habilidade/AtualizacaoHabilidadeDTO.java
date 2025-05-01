package com.inscripto.api.dto.habilidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoHabilidadeDTO(

        @NotNull
        Integer id,

        String habilidade

) {
}
