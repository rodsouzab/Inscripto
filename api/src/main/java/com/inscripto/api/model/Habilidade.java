package com.inscripto.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Habilidade")
@Table(name = "habilidade")
public class Habilidade {

    @Id
    @Column(name = "id")
    private Integer id;

    private String habilidade;
}
