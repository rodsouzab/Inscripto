package com.inscripto.api.dto.restricao;

public record ListagemRestricaoMedicamentoDTO(Integer id, String cpf, String medicamento) {
    public ListagemRestricaoMedicamentoDTO(Integer id, String cpf, String medicamento) {
        this.id = id;
        this.cpf = cpf;
        this.medicamento = medicamento;
    }
}
