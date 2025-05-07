package com.inscripto.api.repository;

import com.inscripto.api.dto.encontro.ListagemEncontroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class EncontroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ListagemEncontroDTO> rowMapper = (rs, rowNum) -> new ListagemEncontroDTO(
            rs.getInt("ano"),
            rs.getString("colegio"),
            rs.getString("tema"),
            rs.getDate("data")
    );

    public void inserirEncontro(Integer ano, String colegio, String tema, Date data) {
        String sql = "INSERT INTO encontro (ano, colegio, tema, data) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ano, colegio, tema, data);
    }

    public List<ListagemEncontroDTO> listarEncontros() {
        String sql = "SELECT ano, colegio, tema, data FROM encontro";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void atualizarEncontro(Integer ano, String colegio, String tema, Date data) {
        String sql = "UPDATE encontro SET colegio = COALESCE(?, colegio), tema = COALESCE(?, tema), data = COALESCE(?, data) WHERE ano = ?";
        jdbcTemplate.update(sql, colegio, tema, data, ano);
    }


    public void deletarPorAno(Integer ano) {
        String updateSql = "UPDATE encontrista SET ano_encontro = NULL WHERE ano_encontro = ?";
        jdbcTemplate.update(updateSql, ano);


        String sql = "DELETE FROM encontro WHERE ano = ?";
        jdbcTemplate.update(sql, ano);
    }
}
