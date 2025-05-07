package com.inscripto.api.repository;

import com.inscripto.api.dto.equipe.ListagemBaseDTO;
import com.inscripto.api.dto.equipe.ListagemNucleoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ListagemBaseDTO> baseRowMapper = (rs, rowNum) -> new ListagemBaseDTO(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("ano"),
            rs.getString("tema")
    );

    private final RowMapper<ListagemNucleoDTO> nucleoRowMapper = (rs, rowNum) -> new ListagemNucleoDTO(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("ano")
    );

    public void inserirEquipe(Integer id, String nome, Integer ano) {
        String sql = "INSERT INTO equipe (id, nome, ano) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, nome, ano);
    }

    public void inserirBase(Integer id, String tema) {
        String sql = "INSERT INTO base (id_equipe, tema) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, tema);
    }

    public void inserirNucleo(Integer id) {
        String sql = "INSERT INTO nucleo (id_equipe) VALUES (?)";
        jdbcTemplate.update(sql, id);
    }

    public List<ListagemBaseDTO> listarBases() {
        String sql = "SELECT id, nome, ano, tema FROM base b" +
                " JOIN equipe e ON b.id_equipe = e.id";
        return jdbcTemplate.query(sql, baseRowMapper);
    }

    public List<ListagemNucleoDTO> listarNucleos() {
        String sql = "SELECT id, nome, ano FROM nucleo n" +
                " JOIN equipe e ON n.id_equipe = e.id";
        return jdbcTemplate.query(sql, nucleoRowMapper);
    }

    public void atualizarBase(Integer id, String nome, Integer ano, String tema) {
        String sqlEquipe = "UPDATE equipe e SET e.nome = COALESCE(?, e.nome), e.ano = COALESCE(?, e.ano) WHERE e.id = ?";
        jdbcTemplate.update(sqlEquipe, nome, ano, id);

        if (tema != null) {
            String sqlBase = "UPDATE base b SET b.tema = COALESCE(?, b.tema) WHERE b.id_equipe = ?";
            jdbcTemplate.update(sqlBase, tema, id);
        }
    }


    public void atualizarNucleo(Integer id, String nome, Integer ano) {
        String sql = "UPDATE equipe SET nome = COALESCE(?, nome), ano = COALESCE(?, ano) WHERE id = ?";
        jdbcTemplate.update(sql, nome, ano, id);
    }



    public void deletarPorId(Integer id) {
        String sql = "DELETE FROM equipe WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
