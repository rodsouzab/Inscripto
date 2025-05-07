package com.inscripto.api.repository;

import com.inscripto.api.model.Encontreiro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EncontreiroRepository {

    private final JdbcTemplate jdbcTemplate;

    public EncontreiroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Encontreiro save(Encontreiro encontreiro) {
        String sql = "INSERT INTO encontreiro (cpf_pessoa, fez_ejc, nome_responsavel, telefone_responsavel) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, encontreiro.getCpf(), encontreiro.isFezEjc(),
                encontreiro.getResponsavel().getNome(),
                encontreiro.getResponsavelTelefone().getTelefone());
        return encontreiro;
    }

    public List<Encontreiro> findAll() {
        String sql = "SELECT * FROM encontreiro";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Encontreiro e = new Encontreiro();
            // Preencher os dados a partir do ResultSet
            return e;
        });
    }

    public Encontreiro findByCpf(String cpf) {
        String sql = "SELECT * FROM encontreiro WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Encontreiro e = new Encontreiro();
            // Preencher os dados a partir do ResultSet
            return e;
        }, cpf);
    }

    public void deleteByCpf(String cpf) {
        String sql = "DELETE FROM encontreiro WHERE cpf_pessoa = ?";
        jdbcTemplate.update(sql, cpf);
    }

    public boolean existsByCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM encontreiro WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, cpf) > 0;
    }
}
