package com.inscripto.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.inscripto.api.model.Pessoa;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PessoaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Pessoa> pessoaMapper = new RowMapper<>() {
        @Override
        public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pessoa p = new Pessoa();
            p.setCpf(rs.getString("cpf"));
            p.setNome(rs.getString("nome"));
            p.setApelido(rs.getString("apelido"));
            p.setData_nascimento(rs.getDate("data_nascimento"));
            p.setTelefone(rs.getString("telefone"));
            p.setFoto_url(rs.getString("foto_url"));
            p.setBairro(rs.getString("bairro"));
            p.setComplemento(rs.getString("complemento"));
            p.setNumero(rs.getInt("numero"));
            p.setRua(rs.getString("rua"));
            p.setCep(rs.getString("cep"));
            p.setInstituicao_ensino(rs.getString("instituicao_ensino"));
            p.setSenha(rs.getString("senha"));
            p.setAdmin(rs.getBoolean("admin"));
            return p;
        }
    };

    public void salvar(Pessoa p) {
        String sql = """
            INSERT INTO pessoa (cpf, nome, apelido, data_nascimento, telefone, foto_url, bairro, complemento, numero, rua, cep, instituicao_ensino, senha, admin)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE 
            nome = VALUES(nome), apelido = VALUES(apelido), data_nascimento = VALUES(data_nascimento), telefone = VALUES(telefone),
            foto_url = VALUES(foto_url), bairro = VALUES(bairro), complemento = VALUES(complemento), numero = VALUES(numero),
            rua = VALUES(rua), cep = VALUES(cep), instituicao_ensino = VALUES(instituicao_ensino), senha = VALUES(senha), admin = VALUES(admin)
        """;

        jdbcTemplate.update(sql,
                p.getCpf(), p.getNome(), p.getApelido(), p.getData_nascimento(), p.getTelefone(),
                p.getFoto_url(), p.getBairro(), p.getComplemento(), p.getNumero(),
                p.getRua(), p.getCep(), p.getInstituicao_ensino(), p.getSenha(), p.isAdmin()
        );
    }

    public void atualizarPessoa(Pessoa p) {
        String sql = """
        UPDATE pessoa SET
            nome = COALESCE(?, nome),
            apelido = COALESCE(?, apelido),
            data_nascimento = COALESCE(?, data_nascimento),
            telefone = COALESCE(?, telefone),
            foto_url = COALESCE(?, foto_url),
            bairro = COALESCE(?, bairro),
            complemento = COALESCE(?, complemento),
            numero = COALESCE(?, numero),
            rua = COALESCE(?, rua),
            cep = COALESCE(?, cep),
            instituicao_ensino = COALESCE(?, instituicao_ensino),
            senha = COALESCE(?, senha),
            admin = COALESCE(?, admin)
        WHERE cpf = ?
    """;

        jdbcTemplate.update(sql,
                p.getNome(), p.getApelido(), p.getData_nascimento(), p.getTelefone(),
                p.getFoto_url(), p.getBairro(), p.getComplemento(), p.getNumero(),
                p.getRua(), p.getCep(), p.getInstituicao_ensino(), p.getSenha(), p.isAdmin(),
                p.getCpf()
        );
    }


    public Optional<Pessoa> findById(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        List<Pessoa> pessoas = jdbcTemplate.query(sql, pessoaMapper, cpf);
        return pessoas.isEmpty() ? Optional.empty() : Optional.of(pessoas.get(0));
    }

    public List<Pessoa> findAll() {
        return jdbcTemplate.query("SELECT * FROM pessoa", pessoaMapper);
    }

    public void deleteById(String cpf) {
        jdbcTemplate.update("DELETE FROM pessoa WHERE cpf = ?", cpf);
    }

    public boolean existsById(String cpf) {
        String sql = "SELECT COUNT(*) FROM pessoa WHERE cpf = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, cpf);
        return count != null && count > 0;
    }
}
