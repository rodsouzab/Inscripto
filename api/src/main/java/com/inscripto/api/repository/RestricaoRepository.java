package com.inscripto.api.repository;

import com.inscripto.api.dto.restricao.ListagemRestricaoAlimentoDTO;
import com.inscripto.api.dto.restricao.ListagemRestricaoMedicamentoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestricaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public RestricaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void criarRestricaoAlimento(String cpf, String alimento) {
        String sql = "INSERT INTO restricao_alimento (cpf, alimento) VALUES (?, ?)";
        jdbcTemplate.update(sql, cpf, alimento);
    }

    public void criarRestricaoMedicamento(String cpf, String medicamento) {
        String sql = "INSERT INTO restricao_medicamento (cpf, medicamento) VALUES (?, ?)";
        jdbcTemplate.update(sql, cpf, medicamento);
    }

    public List<ListagemRestricaoAlimentoDTO> listarRestricaoAlimento() {
        String sql = "SELECT * FROM restricao_alimento";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ListagemRestricaoAlimentoDTO(
                rs.getInt("id"), rs.getString("cpf"), rs.getString("alimento")
        ));
    }

    public List<ListagemRestricaoMedicamentoDTO> listarRestricaoMedicamento() {
        String sql = "SELECT * FROM restricao_medicamento";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ListagemRestricaoMedicamentoDTO(
                rs.getInt("id"), rs.getString("cpf"), rs.getString("medicamento")
        ));
    }

    public void atualizarRestricaoAlimento(Integer id, String alimento) {
        String sql = "UPDATE restricao_alimento SET alimento = ? WHERE id = ?";
        jdbcTemplate.update(sql, alimento, id);
    }

    public void atualizarRestricaoMedicamento(Integer id, String medicamento) {
        String sql = "UPDATE restricao_medicamento SET medicamento = ? WHERE id = ?";
        jdbcTemplate.update(sql, medicamento, id);
    }

    public void excluirRestricaoAlimentoPorId(Integer id) {
        String sql = "DELETE FROM restricao_alimento WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void excluirRestricaoAlimentoPorCpf(String cpf) {
        String sql = "DELETE FROM restricao_alimento WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }

    public void excluirRestricaoMedicamentoPorId(Integer id) {
        String sql = "DELETE FROM restricao_medicamento WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void excluirRestricaoMedicamentoPorCpf(String cpf) {
        String sql = "DELETE FROM restricao_medicamento WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
