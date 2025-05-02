package com.inscripto.api.repository;

import com.inscripto.api.dto.restricao.ListagemRestricaoAlimentoDTO;
import com.inscripto.api.dto.restricao.ListagemRestricaoMedicamentoDTO;
import com.inscripto.api.model.RestricaoMedicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestricaoMedicamentoRepository extends JpaRepository<RestricaoMedicamento, Integer> {

    @Modifying
    @Query(value = "INSERT INTO restricao_medicamento (cpf, medicamento) VALUES (:cpf, :medicamento)", nativeQuery = true)
    void criarRestricaoMedicamento(@Param("cpf") String cpf, @Param("medicamento") String medicamento);

    @Query(value = """
    SELECT new com.inscripto.api.dto.restricao.ListagemRestricaoMedicamentoDTO(rm.id, rm.pessoa.cpf, rm.medicamento)
    FROM RestricaoMedicamento rm
""", countQuery = "SELECT COUNT(rm) FROM RestricaoMedicamento rm")
    Page<ListagemRestricaoMedicamentoDTO> listarRestricaoMedicamento(Pageable paginacao);

    @Modifying
    @Query("UPDATE RestricaoMedicamento rm SET rm.medicamento = COALESCE(:medicamento, rm.medicamento) WHERE rm.id = :id")
    void editarRestricaoMedicamento(@Param("id") String id, @Param("medicamento") String medicamento);

    @Modifying
    @Query(value = "DELETE FROM `restricao_medicamento` WHERE id = :id", nativeQuery = true)
    void deletarPorID(Integer id);

    @Modifying
    @Query(value = "DELETE FROM `restricao_medicamento` WHERE cpf = :cpf", nativeQuery = true)
    void deletarPorCpf(String cpf);
}
