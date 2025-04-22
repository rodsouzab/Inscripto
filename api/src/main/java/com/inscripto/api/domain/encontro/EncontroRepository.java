package com.inscripto.api.domain.encontro;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EncontroRepository extends JpaRepository<Encontro, String> {

    @Modifying
    @Query(value = "INSERT INTO encontro (ano, colegio, tema, data) VALUES (:ano, :colegio, :tema, :data)", nativeQuery = true)
    void inserirEncontro(@Param("ano") String ano, @Param("colegio") String colegio, @Param("tema") String tema, @Param("data") java.sql.Date data);

    @Query(value = """
        SELECT new com.inscripto.api.domain.encontro.ListagemEncontroDTO(e.ano, e.colegio, e.tema, e.data)
        FROM Encontro e
    """, countQuery = "SELECT COUNT(e) FROM Encontro e")
    Page<ListagemEncontroDTO> listarEncontros(Pageable pageable);

    @Modifying
    @Query("UPDATE Encontro e SET e.colegio = COALESCE(:colegio, e.colegio), e.tema = COALESCE(:tema, e.tema), e.data = COALESCE(:data, e.data) WHERE e.ano = :ano")
    void atualizarEncontro(@Param("ano") String ano, @Param("colegio") String colegio, @Param("tema") String tema, @Param("data") java.sql.Date data);

    @Modifying
    @Query(value = "DELETE FROM encontro WHERE ano = :ano", nativeQuery = true)
    void deletarPorAno(@Param("ano") String ano);

}
