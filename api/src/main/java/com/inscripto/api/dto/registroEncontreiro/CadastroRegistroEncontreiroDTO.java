package com.inscripto.api.dto.registroEncontreiro;


import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.model.Encontro;
import com.inscripto.api.model.Equipe;

public record CadastroRegistroEncontreiroDTO(

        Encontro encontro,
        Equipe equipe,
        Encontreiro encontreiro
) {
}
