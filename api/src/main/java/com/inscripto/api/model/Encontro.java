package com.inscripto.api.model;

import com.inscripto.api.dto.encontro.CadastroEncontroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@EqualsAndHashCode(of = "ano")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Encontro")
@Table(name = "encontro")
public class Encontro {

    @Id
    @Column(name = "ano", nullable = false)
    private Integer ano;

    private String colegio;

    private String tema;

    private Date data;

    public Encontro(CadastroEncontroDTO dto) {
        this.ano = dto.ano();
        this.colegio = dto.colegio();
        this.tema = dto.tema();
        this.data = dto.data();
    }

    public void atualizarDados(@Valid CadastroEncontroDTO dto) {
        if (dto.colegio() != null) {
            this.colegio = dto.colegio();
        }
        if (dto.tema() != null) {
            this.tema = dto.tema();
        }
        if (dto.data() != null) {
            this.data = dto.data();
        }
    }

    public Integer getAno() {
        return ano;
    }

    public String getColegio() {
        return colegio;
    }

    public String getTema() {
        return tema;
    }

    public Date getData() {
        return data;
    }
}
