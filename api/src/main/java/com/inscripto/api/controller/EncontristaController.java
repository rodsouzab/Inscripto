package com.inscripto.api.controller;

import com.inscripto.api.model.Encontrista;
import com.inscripto.api.repository.EncontristaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@RestController
@RequestMapping("/encontristas")
public class EncontristaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final EncontristaRepository encontristaRepository;

    public EncontristaController(EncontristaRepository encontristaRepository) {
        this.encontristaRepository = encontristaRepository;
    }

    @PostMapping
    @Transactional
    public Encontrista newEncontrista(@RequestBody @Valid Encontrista newEncontrista) {
        entityManager.persist(newEncontrista);
        return newEncontrista;
    }

    @GetMapping
    public List<Encontrista> getAllEncontristas() {
        Query query = entityManager.createQuery("SELECT e FROM Encontrista e", Encontrista.class);
        return query.getResultList();
    }

    @GetMapping("/{cpf}")
    public Optional<Encontrista> getEncontristaById(@PathVariable String cpf) {
        Encontrista encontrista = entityManager.find(Encontrista.class, cpf);
        return Optional.ofNullable(encontrista);
    }

    @PutMapping("/{cpf}")
    @Transactional
    public Encontrista updateEncontrista(@PathVariable String cpf, @RequestBody @Valid Encontrista updatedEncontrista) {
        Encontrista encontrista = entityManager.find(Encontrista.class, cpf);
        if (encontrista == null) {
            throw new RuntimeException("Encontrista não encontrado com CPF: " + cpf);
        }
        // Atualize os campos necessários
        encontrista.setPaisSeparados(updatedEncontrista.isPaisSeparados());
        encontrista.setEncontro(updatedEncontrista.getEncontro());
        encontrista.setNucleo(updatedEncontrista.getNucleo());
        entityManager.merge(encontrista);
        return encontrista;
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public void deleteEncontrista(@PathVariable String cpf) {
        Encontrista encontrista = entityManager.find(Encontrista.class, cpf);
        if (encontrista != null) {
            entityManager.remove(encontrista);
        }
    }
}
