package com.inscripto.api.repository;

import com.inscripto.api.model.Responsavel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponsavelRepository {

    private final JdbcTemplate jdbcTemplate;

    public ResponsavelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void criarResponsavel(Responsavel responsavel) {
        String sql = "INSERT INTO responsavel (telefone, nome) VALUES (?, ?)";
        jdbcTemplate.update(sql, responsavel.getTelefone(), responsavel.getNome());
    }

    public List<Responsavel> listarResponsaveis() {
        String sql = "SELECT * FROM responsavel";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Responsavel responsavel = new Responsavel();
            responsavel.setTelefone(rs.getString("telefone"));
            responsavel.setNome(rs.getString("nome"));
            return responsavel;
        });
    }

    public int deletarResponsavelPorTelefone(String telefone) {
        String sql = "DELETE FROM responsavel WHERE telefone = ?";
        return jdbcTemplate.update(sql, telefone);
    }

    public int atualizarNomeResponsavel(String telefone, String nome) {
        String sql = "UPDATE responsavel SET nome = ? WHERE telefone = ?";
        return jdbcTemplate.update(sql, nome, telefone);
    }
}
