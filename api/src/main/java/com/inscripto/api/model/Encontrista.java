package com.inscripto.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Encontrista")
@Table(name = "encontrista")
@PrimaryKeyJoinColumn(name = "cpf_pessoa", referencedColumnName = "cpf")
public class Encontrista extends Pessoa{

    private boolean paisSeparados;

    @ManyToOne
    @JoinColumn(name = "ano_encontro", referencedColumnName = "ano")
    private Encontro encontro;

    @ManyToOne
    @JoinColumn(name = "id_nucleo", referencedColumnName = "id_equipe")
    private Nucleo nucleo;
}
