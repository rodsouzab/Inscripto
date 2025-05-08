package com.inscripto.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Encontrista extends Pessoa{

    private boolean paisSeparados;
    private Encontro encontro;
    private Nucleo nucleo;

    public boolean isPaisSeparados() {
        return paisSeparados;
    }

    public Encontro getEncontro() {
        return encontro;
    }
    public Nucleo getNucleo() {
        return nucleo;
    }
}
