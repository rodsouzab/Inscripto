package com.inscripto.api.dto.restricao;

import jakarta.validation.constraints.NotBlank;

public record CadastroRestricaoMedicamentoDTO(

        @NotBlank
        String cpf,

        @NotBlank
        String medicamento
) {
}
