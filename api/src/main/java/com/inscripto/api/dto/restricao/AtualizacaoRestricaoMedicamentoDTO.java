package com.inscripto.api.dto.restricao;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoRestricaoMedicamentoDTO(

        @NotNull
        Integer id,

        String medicamento
) {
}
