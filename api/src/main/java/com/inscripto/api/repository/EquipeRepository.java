package com.inscripto.api.repository;

import com.inscripto.api.dto.equipe.ListagemBaseDTO;
import com.inscripto.api.dto.equipe.ListagemNucleoDTO;
import com.inscripto.api.model.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    @Modifying
    @Query(value = "INSERT INTO equipe (id, nome, ano) VALUES (:id, :nome, :ano)", nativeQuery = true)
    void inserirEquipe(@Param("id") Integer id, @Param("nome") String nome, @Param("ano") Integer ano);

    @Modifying
    @Query(value = "INSERT INTO base (id_equipe, tema) VALUES (:id, :tema)", nativeQuery = true)
    void inserirBase(@Param("id") Integer id, @Param("tema") String tema);

    @Modifying
    @Query(value = "INSERT INTO nucleo (id_equipe) VALUES (:id)", nativeQuery = true)
    void inserirNucleo(@Param("id") Integer id);


    @Query(value = """
    SELECT new com.inscripto.api.dto.equipe.ListagemBaseDTO(b.id, b.nome, b.ano, b.tema)
    FROM Base b
""", countQuery = "SELECT COUNT(b) FROM Base b")
    Page<ListagemBaseDTO> listarBases(Pageable pageable);

    @Query(value = """
    SELECT new com.inscripto.api.dto.equipe.ListagemNucleoDTO(n.id, n.nome, n.ano)
    FROM Nucleo n
""", countQuery = "SELECT COUNT(n) FROM Nucleo n")
    Page<ListagemNucleoDTO> listarNucleos(Pageable pageable);

    @Modifying
    @Query("UPDATE Base b SET b.nome = COALESCE(:nome, b.nome), b.ano = COALESCE(:ano, b.ano), b.tema = COALESCE(:tema, b.tema) WHERE b.id = :id")
    void atualizarBase(@Param("id") Integer id, @Param("nome") String nome, @Param("ano") Integer ano, @Param("tema") String tema);

    @Modifying
    @Query("UPDATE Nucleo n SET n.nome = COALESCE(:nome, n.nome), n.ano = COALESCE(:ano, n.ano) WHERE n.id = :id")
    void atualizarNucleo(@Param("id") Integer id, @Param("nome") String nome, @Param("ano") Integer ano);

    @Modifying
    @Query(value = "DELETE FROM `equipe` WHERE id = :id", nativeQuery = true)
    void deletarPorId(@Param("id") Integer id);


}
