package com.inscripto.api.repository;

import com.inscripto.api.dto.registroEncontreiro.ChaveRegistroEncontreiroDTO;
import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.model.Encontro;
import com.inscripto.api.model.Equipe;
import com.inscripto.api.model.RegistroEncontreiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistroEncontreiroRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistroEncontreiroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirRegistroEncontreiro(RegistroEncontreiro registroEncontreiro) {
        String sql = "INSERT INTO registro_encontreiro (ano_encontro, id_equipe, cpf_encontreiro) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, registroEncontreiro.getEncontro().getAno(),
                registroEncontreiro.getEquipe().getId(),
                registroEncontreiro.getEncontreiro().getCpf());
    }


    public List<ChaveRegistroEncontreiroDTO> listarRegistros() {
        String sql = "SELECT ano_encontro, id_equipe, cpf_encontreiro FROM registro_encontreiro";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ChaveRegistroEncontreiroDTO(
                rs.getInt("ano_encontro"),
                rs.getInt("id_equipe"),
                rs.getString("cpf_encontreiro")
        ));
    }


    public void atualizarEquipeRegistro(RegistroEncontreiro registroEncontreiro) {
        String sql = "UPDATE registro_encontreiro SET id_equipe = ? WHERE ano_encontro = ? AND cpf_encontreiro = ?";
        jdbcTemplate.update(sql,
                registroEncontreiro.getEquipe().getId(),
                registroEncontreiro.getEncontro().getAno(),
                registroEncontreiro.getEncontreiro().getCpf());

    }

    public void atualizarEncontroRegistro(RegistroEncontreiro registroEncontreiro) {
        String sql = "UPDATE registro_encontreiro SET ano_encontro = ? WHERE id_equipe = ? AND cpf_encontreiro = ?";
        jdbcTemplate.update(sql,
                registroEncontreiro.getEncontro().getAno(),
                registroEncontreiro.getEquipe().getId(),
                registroEncontreiro.getEncontreiro().getCpf());

    }

    public void removerRegistro(RegistroEncontreiro registroEncontreiro) {

        String sql = "DELETE FROM registro_encontreiro WHERE ano_encontro = ? AND id_equipe = ? AND cpf_encontreiro = ?";
        jdbcTemplate.update(sql,
                registroEncontreiro.getEncontro().getAno(),
                registroEncontreiro.getEquipe().getId(),
                registroEncontreiro.getEncontreiro().getCpf());
    }
}
