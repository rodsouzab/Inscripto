package com.inscripto.api.dto.registroEncontreiro;

import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.model.Encontro;
import com.inscripto.api.model.Equipe;
import com.inscripto.api.model.RegistroEncontreiro;

public record ListagemRegistroEncontreiroDTO(

        Encontro encontro,
        Equipe equipe,
        Encontreiro encontreiro
) {

    public ListagemRegistroEncontreiroDTO(RegistroEncontreiro registroEncontreiro) {
        this(
                registroEncontreiro.getEncontro(),
                registroEncontreiro.getEquipe(),
                registroEncontreiro.getEncontreiro()
        );
    }
}
