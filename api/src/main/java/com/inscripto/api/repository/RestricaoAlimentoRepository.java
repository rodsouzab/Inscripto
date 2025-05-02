package com.inscripto.api.repository;

import com.inscripto.api.dto.restricao.ListagemRestricaoAlimentoDTO;
import com.inscripto.api.model.RestricaoAlimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestricaoAlimentoRepository extends JpaRepository<RestricaoAlimento, Integer> {

    @Modifying
    @Query(value = "INSERT INTO restricao_alimento (cpf, alimento) VALUES (:cpf, :alimento)", nativeQuery = true)
    void criarRestricaoAlimento(@Param("cpf") String cpf, @Param("alimento") String alimento);

    @Query(value = """
    SELECT new com.inscripto.api.dto.restricao.ListagemRestricaoAlimentoDTO(ra.id, ra.pessoa.cpf, ra.alimento)
    FROM RestricaoAlimento ra
""", countQuery = "SELECT COUNT(ra) FROM RestricaoAlimento ra")
    Page<ListagemRestricaoAlimentoDTO> listarRestricaoAlimento(Pageable paginacao);

    @Modifying
    @Query("UPDATE RestricaoAlimento ra SET ra.alimento = COALESCE(:alimento, ra.alimento) WHERE ra.id = :id")
    void editarRestricaoAlimento(@Param("id") String id, @Param("alimento") String alimento);

    @Modifying
    @Query(value = "DELETE FROM `restricao_alimento` WHERE id = :id", nativeQuery = true)
    void deletarPorID(Integer id);

    @Modifying
    @Query(value = "DELETE FROM `restricao_alimento` WHERE cpf = :cpf", nativeQuery = true)
    void deletarPorCpf(String cpf);
}
