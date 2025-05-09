/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.inscripto.api.repository;

import com.inscripto.api.model.EhFamiliar;
import com.inscripto.api.model.Encontreiro;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


/**
 *
 * @author ticolinux
 */

@Repository
public class EhFamiliarRepository {

    private final JdbcTemplate jdbcTemplate;

    public EhFamiliarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EhFamiliar save(EhFamiliar ehFamiliar) {
        String sql = "INSERT INTO eh_familiar (cpf_encontreiro1, cpf_encontreiro2, trabalhar_junto, relacao) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            ehFamiliar.getEncontreiro1().getCpf(),
            ehFamiliar.getEncontreiro2().getCpf(),
            ehFamiliar.isTrabalhar_junto(),
            ehFamiliar.getRelacao()
        );
        return ehFamiliar;
    }

    public List<EhFamiliar> findAll(){
        String sql = "SELECT * FROM eh_familiar";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            EhFamiliar ef = new EhFamiliar();
            ef.setEncontreiro1(new Encontreiro());
            ef.getEncontreiro1().setCpf(rs.getString("cpf_encontreiro1"));
            ef.setEncontreiro2(new Encontreiro());
            ef.getEncontreiro2().setCpf(rs.getString("cpf_encontreiro2"));
            ef.setTrabalhar_junto(rs.getBoolean("trabalhar_junto"));
            ef.setRelacao(rs.getString("relacao"));
            return ef;
        });
    }

    public void atualizarEhFamiliar(EhFamiliar ef) {
        // Atualizar os dados do EhFamiliar
        String sql = "UPDATE eh_familiar SET trabalhar_junto = ?, relacao = ? WHERE cpf_encontreiro1 = ? AND cpf_encontreiro2 = ?";
        jdbcTemplate.update(sql,
                ef.isTrabalhar_junto(),
                ef.getRelacao(),
                ef.getEncontreiro1().getCpf(),
                ef.getEncontreiro2().getCpf());
    }

    public void deleteByCpf(String cpf1, String cpf2) {
        String sql = "DELETE FROM eh_familiar WHERE cpf_encontreiro1 = ? AND cpf_encontreiro2 = ?";
        jdbcTemplate.update(sql, cpf1, cpf2);
    }
}

