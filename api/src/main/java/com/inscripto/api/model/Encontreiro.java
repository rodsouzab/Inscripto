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
@Entity(name = "Encontreiro")
@Table(name = "encontreiro")
@PrimaryKeyJoinColumn(name = "cpf_pessoa", referencedColumnName = "cpf")
public class Encontreiro extends Pessoa {

    @Column(name = "fez_ejc", nullable = false)
    private boolean fezEjc;

    @ManyToOne
    @JoinColumn(name = "nome_responsavel", referencedColumnName = "nome", nullable = false)
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "telefone_responsavel", referencedColumnName = "telefone", nullable = false)
    private Responsavel responsavelTelefone;
}
