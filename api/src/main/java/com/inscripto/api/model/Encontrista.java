package com.inscripto.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Encontrista")
@Table(name = "encontrista")
@PrimaryKeyJoinColumn(name = "cpf_pessoa", referencedColumnName = "cpf")
public class Encontrista extends Pessoa{

    @Column(name = "pais_separados")
    private boolean paisSeparados;

    public boolean isPaisSeparados() {
        return paisSeparados;
    }

    public void setPaisSeparados(boolean paisSeparados) {
        this.paisSeparados = paisSeparados;
    }

    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }
    public Nucleo getNucleo() {
        return nucleo;
    }

    public void setNucleo(Nucleo nucleo) {
        this.nucleo = nucleo;
    }

    @ManyToOne
    @JoinColumn(name = "ano_encontro", referencedColumnName = "ano")
    private Encontro encontro;

    @ManyToOne
    @JoinColumn(name = "id_nucleo", referencedColumnName = "id_equipe")
    private Nucleo nucleo;
}
