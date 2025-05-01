package com.inscripto.api.model;

import com.inscripto.api.dto.equipe.CadastroBaseDTO;
import com.inscripto.api.dto.equipe.CadastroEquipeDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "Equipe")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "equipe")
public class Equipe {

    @Id
    @Column(name = "id")
    private Integer id;

    private String nome;

    private Integer ano;

    public Equipe(CadastroEquipeDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.ano = dto.ano();
    }

    public void atualizarDados(@Valid CadastroBaseDTO dto) {
    }
}
