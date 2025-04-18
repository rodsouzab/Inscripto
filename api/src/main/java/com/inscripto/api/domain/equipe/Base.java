package com.inscripto.api.domain.equipe;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "Base")
@Table(name = "base")
@PrimaryKeyJoinColumn(name = "id_equipe")
public class Base extends Equipe {
    private String tema;

    public Base(CadastroBaseDTO dto) {
        super(new CadastroEquipeDTO(dto.id(), dto.nome(), dto.ano()));
        this.tema = dto.tema();
    }
}