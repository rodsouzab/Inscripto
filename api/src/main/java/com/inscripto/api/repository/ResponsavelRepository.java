package com.inscripto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inscripto.api.model.Responsavel;
import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, String> {

}