package com.inscripto.api.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inscripto.api.model.Coordenador;
import com.inscripto.api.model.Pessoa;

@Repository
public class CoordenadorRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public CoordenadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coordenador save(Coordenador coordenador) {
        String sql = "INSERT INTO coordenador (cpf_pessoa) VALUES (?)";
        jdbcTemplate.update(sql, coordenador.getCpf());
        return coordenador;
    }

    public List<Coordenador> findAll() {
        String sql = "SELECT * FROM coordenador";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Coordenador c = new Coordenador();
            c.setCpf(rs.getString("cpf_pessoa"));
            return c;
        });
    }

    public void atualizarCoordenador(Coordenador c) {
        // Como só existe o campo cpf_pessoa, não há o que atualizar além de garantir que existe
        // Este método pode ser um no-op ou lançar erro se tentar alterar o CPF
        // Aqui, apenas mantemos a assinatura para compatibilidade
        // Se desejar atualizar para um novo CPF, seria necessário deletar e inserir novamente
    }

    public Coordenador findByCpf(String cpf) {
        String sql = "SELECT * FROM coordenador WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Coordenador c = new Coordenador();
            c.setCpf(rs.getString("cpf_pessoa"));
            // ...preencher outros campos herdados de Pessoa, se necessário...
            return c;
        }, cpf);
    }

    public void deleteByCpf(String cpf) {
        String sql = "DELETE FROM coordenador WHERE cpf_pessoa = ?";
        jdbcTemplate.update(sql, cpf);
    }

    public boolean existsByCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM coordenador WHERE cpf_pessoa = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, cpf) > 0;
    }
}
