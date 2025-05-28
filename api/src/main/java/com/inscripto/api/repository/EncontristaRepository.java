package com.inscripto.api.repository;

import com.inscripto.api.model.Encontrista;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EncontristaRepository {

    private final JdbcTemplate jdbcTemplate;

    public EncontristaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Encontrista save(Encontrista encontrista) {
        String sql = "INSERT INTO encontrista (cpf_pessoa, pais_separados, ano_encontro, id_nucleo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, encontrista.getCpf(), encontrista.isPaisSeparados(),
                encontrista.getEncontro().getAno(), encontrista.getNucleo().getId());
        return encontrista;
    }

    public List<Encontrista> findAll() {
        String sql = "SELECT * FROM encontrista";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Encontrista e = new Encontrista();
            // Preencher os dados a partir do ResultSet
            return e;
        });
    }

    public Encontrista findByCpf(String cpf) {
        String sql = "SELECT * FROM encontrista WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Encontrista e = new Encontrista();
            // Preencher os dados a partir do ResultSet
            return e;
        }, cpf);
    }

    public void deleteByCpf(String cpf) {
        String sql = "DELETE FROM encontrista WHERE cpf_pessoa = ?";
        jdbcTemplate.update(sql, cpf);
    }

    public boolean existsByCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM encontrista WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, cpf) > 0;
    }
    public void criarRelacionamentoResponsavelEncontrista(String telefoneResponsavel, String cpfEncontrista) {
    String sql = "INSERT INTO responsavel_encontrista (telefone_responsavel, cpf_encontrista) VALUES (?, ?)";
    jdbcTemplate.update(sql, telefoneResponsavel, cpfEncontrista);
}

}