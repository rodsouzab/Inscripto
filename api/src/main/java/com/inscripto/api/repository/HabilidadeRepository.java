package com.inscripto.api.repository;

import com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HabilidadeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ListagemHabilidadeDTO> habilidadeRowMapper = (rs, rowNum) -> new ListagemHabilidadeDTO(
            rs.getInt("id"),
            rs.getString("habilidade")
    );

    public void criarHabilidade(Integer id, String habilidade) {
        String sql = "INSERT INTO habilidade (id, habilidade) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, habilidade);
    }

    public List<ListagemHabilidadeDTO> listarHabilidade() {
        String sql = "SELECT id, habilidade FROM habilidade";
        return jdbcTemplate.query(sql, habilidadeRowMapper);
    }

    public void editarHabilidade(Integer id, String habilidade) {
        String sql = "UPDATE habilidade SET habilidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, habilidade, id);
    }

    public void deletarPorID(Integer id) {
        String sql = "DELETE FROM habilidade WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
