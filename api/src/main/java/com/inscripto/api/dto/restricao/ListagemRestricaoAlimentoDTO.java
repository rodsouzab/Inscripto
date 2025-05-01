package com.inscripto.api.dto.restricao;

public record ListagemRestricaoAlimentoDTO(Integer id, String cpf, String alimento) {
    public ListagemRestricaoAlimentoDTO(Integer id, String cpf, String alimento) {
        this.id = id;
        this.cpf = cpf;
        this.alimento = alimento;
    }
}
