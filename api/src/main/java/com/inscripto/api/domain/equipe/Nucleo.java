package com.inscripto.api.domain.equipe;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "Nucleo")
@Table(name = "nucleo")
@PrimaryKeyJoinColumn(name = "id_equipe") // Corrigido aqui
public class Nucleo extends Equipe {
    // Atributos estão todos na superclasse

    public Nucleo(CadastroNucleoDTO dto) {
        super(new CadastroEquipeDTO(dto.id(), dto.nome(), dto.ano()));
    }
}
