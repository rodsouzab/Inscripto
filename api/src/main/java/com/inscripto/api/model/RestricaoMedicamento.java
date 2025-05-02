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
@Entity(name = "RestricaoMedicamento")
@Table(name = "restricao_medicamento")
public class RestricaoMedicamento {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Pessoa pessoa;

    private String medicamento;
}
