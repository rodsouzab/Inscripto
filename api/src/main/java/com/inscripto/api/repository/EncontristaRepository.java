package com.inscripto.api.repository;

import com.inscripto.api.model.Encontrista;
import com.inscripto.api.model.Encontro;
import com.inscripto.api.model.Nucleo;

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
        e.setCpf(rs.getString("cpf_pessoa"));
        e.setPaisSeparados(rs.getBoolean("pais_separados"));
        
        // Para os objetos relacionamento (Encontro, Nucleo) você precisaria montar os objetos ou fazer join com outras tabelas
        Encontro encontro = new Encontro();
        encontro.setAno(rs.getInt("ano_encontro"));
        e.setEncontro(encontro);
        
        Nucleo nucleo = new Nucleo();
        nucleo.setId(rs.getInt("id_nucleo"));
        e.setNucleo(nucleo);

        // Se Pessoa tem outros campos, se herdam, setar aqui também (ex: nome, telefone, etc)
        // e.setNome(rs.getString("nome")); // se estiver no ResultSet

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