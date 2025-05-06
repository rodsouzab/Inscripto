package com.inscripto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.inscripto.api.model.Responsavel;
import java.util.List;

@RestController
public class ResponsavelController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/responsavel")
    public Responsavel newResponsavel(@RequestBody Responsavel newResponsavel) {
        String sql = "INSERT INTO responsavel (telefone, nome) VALUES (?, ?)";
        jdbcTemplate.update(sql, newResponsavel.getTelefone(), newResponsavel.getNome());
        return newResponsavel;
    }

    @GetMapping("/responsavel")
    public List<Responsavel> getAllResponsaveis() {
        String sql = "SELECT * FROM responsavel";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Responsavel responsavel = new Responsavel();
            responsavel.setTelefone(rs.getString("telefone"));
            responsavel.setNome(rs.getString("nome"));
            return responsavel;
        });
    }

    @DeleteMapping("/responsavel/{telefone}")
    public void deleteResponsavel(@PathVariable String telefone) {
        String sql = "DELETE FROM responsavel WHERE telefone = ?";
        int rowsAffected = jdbcTemplate.update(sql, telefone);
        if (rowsAffected == 0) {
            throw new RuntimeException("Responsável com telefone " + telefone + " não encontrado.");
        }
    }

    @PutMapping("/responsavel/{telefone}")
    public Responsavel updateNomeResponsavel(@PathVariable String telefone, @RequestBody Responsavel updatedResponsavel) {
        String sql = "UPDATE responsavel SET nome = ? WHERE telefone = ?";
        int rowsAffected = jdbcTemplate.update(sql, updatedResponsavel.getNome(), telefone);
        if (rowsAffected == 0) {
            throw new RuntimeException("Responsável com telefone " + telefone + " não encontrado.");
        }
        return updatedResponsavel;
    }
}
