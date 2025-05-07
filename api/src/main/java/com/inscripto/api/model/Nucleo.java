package com.inscripto.api.model;

import com.inscripto.api.dto.equipe.CadastroEquipeDTO;
import com.inscripto.api.dto.equipe.CadastroNucleoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class Nucleo extends Equipe {
    // Atributos estão todos na superclasse

    public Nucleo(CadastroNucleoDTO dto) {
        super(new CadastroEquipeDTO(dto.id(), dto.nome(), dto.ano()));
    }
}
