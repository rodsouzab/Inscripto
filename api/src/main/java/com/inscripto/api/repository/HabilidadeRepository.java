package com.inscripto.api.repository;

import com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO;
import com.inscripto.api.model.Habilidade;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {
    @Modifying
    @Query(value = "INSERT INTO habilidade (id, habilidade) VALUES (:id, :habilidade)", nativeQuery = true)
    void criarHabilidade(@Param("id") Integer id, @Param("habilidade") String habilidade);

    @Query(value = """
    SELECT new com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO(h.id, h.habilidade)
    FROM Habilidade h
""", countQuery = "SELECT COUNT(h) FROM Habilidade h")
    Page<ListagemHabilidadeDTO> listarHabilidade(Pageable paginacao);

    @Modifying
    @Query("UPDATE Habilidade h SET h.habilidade = COALESCE(:habilidade, h.habilidade) WHERE h.id = :id")
    void editarHabilidade(@Param("id") Integer id,@Param("habilidade") String habilidade);

    @Modifying
    @Query(value = "DELETE FROM `habilidade` WHERE id = :id", nativeQuery = true)
    void deletarPorID(Integer id);
}
