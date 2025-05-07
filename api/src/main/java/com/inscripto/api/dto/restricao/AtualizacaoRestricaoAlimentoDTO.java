package com.inscripto.api.dto.restricao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoRestricaoAlimentoDTO(

        @NotNull
        Integer id,

        String alimento
) {
}
