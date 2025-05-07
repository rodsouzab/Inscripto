package com.inscripto.api.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor

public class RestricaoAlimento {


    private Integer id;

    private Pessoa pessoa;

    private String alimento;
}
