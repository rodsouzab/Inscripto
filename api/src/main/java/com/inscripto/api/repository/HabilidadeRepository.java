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

    public void vincularHabilidadeAoEncontreiro(String cpfEncontreiro, Integer idHabilidade) {
    String sql = "INSERT INTO habilidades_encontreiro (cpf_encontreiro, id_habilidade) VALUES (?, ?)";
    jdbcTemplate.update(sql, cpfEncontreiro, idHabilidade);
}

    public List<ListagemHabilidadeDTO> listarHabilidadesPorEncontreiro(String cpfEncontreiro) {
        String sql = """
                SELECT h.id, h.habilidade
                FROM habilidades_encontreiro he
                JOIN habilidade h ON he.id_habilidade = h.id
                WHERE he.cpf_encontreiro = ?
                """;
        return jdbcTemplate.query(sql, habilidadeRowMapper, cpfEncontreiro);
    }

    public void removerHabilidadeDeEncontreiro(String cpfEncontreiro, Integer idHabilidade) {
        String sql = "DELETE FROM habilidades_encontreiro WHERE cpf_encontreiro = ? AND id_habilidade = ?";
        jdbcTemplate.update(sql, cpfEncontreiro, idHabilidade);
    }


}
