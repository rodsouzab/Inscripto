package com.inscripto.api.repository;

import com.inscripto.api.model.Encontrista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncontristaRepository extends JpaRepository<Encontrista, String> {
}
