package com.inscripto.api.controller;

import com.inscripto.api.dto.habilidade.AtualizacaoHabilidadeDTO;
import com.inscripto.api.dto.habilidade.CadastroHabilidadeDTO;
import com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO;
import com.inscripto.api.dto.restricao.*;
import com.inscripto.api.repository.HabilidadeRepository;
import com.inscripto.api.repository.RestricaoAlimentoRepository;
import com.inscripto.api.repository.RestricaoMedicamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restricoes")
public class RestricaoController {

    @Autowired
    private final RestricaoAlimentoRepository alimentoRepository;
    @Autowired
    private final RestricaoMedicamentoRepository medicamentoRepository;

    public RestricaoController(RestricaoAlimentoRepository alimentoRepository, RestricaoMedicamentoRepository medicamentoRepository) {
        this.alimentoRepository = alimentoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @PostMapping("/alimentos")
    @Transactional
    public void criarRestricaoAlimento(@RequestBody CadastroRestricaoAlimentoDTO dto){
        alimentoRepository.criarRestricaoAlimento(dto.cpf(), dto.alimento());
    }

    @PostMapping("/medicamentos")
    @Transactional
    public void criarRestricaoMedicamentos(@RequestBody CadastroRestricaoMedicamentoDTO dto){
        medicamentoRepository.criarRestricaoMedicamento(dto.cpf(), dto.medicamento());
    }


    @GetMapping("/alimentos")
    public Page<ListagemRestricaoAlimentoDTO> listarRestricaoAlimento(@PageableDefault(sort = {"pessoa.cpf"}, size = 10) Pageable paginacao){
        return alimentoRepository.listarRestricaoAlimento(paginacao);
    }

    @GetMapping("/medicamentos")
    public Page<ListagemRestricaoMedicamentoDTO> listarRestricaoMedicamento(@PageableDefault(sort = {"pessoa.cpf"}, size = 10) Pageable paginacao){
        return medicamentoRepository.listarRestricaoMedicamento(paginacao);
    }


    @PutMapping("/alimentos")
    @Transactional
    public void atualizarRestricaoAlimento(@RequestBody AtualizacaoRestricaoAlimentoDTO dto){
        alimentoRepository.editarRestricaoAlimento(dto.id(), dto.alimento());
    }

    @PutMapping("/medicamentos")
    @Transactional
    public void atualizarRestricaoMedicamento(@RequestBody AtualizacaoRestricaoMedicamentoDTO dto){
        medicamentoRepository.editarRestricaoMedicamento(dto.id(), dto.medicamento());
    }

    @DeleteMapping("/alimentos/id/{id}")
    @Transactional
    public void excluirRestricaoAlimentoPorId(@PathVariable Integer id){
        alimentoRepository.deletarPorID(id);
    }

    @DeleteMapping("/alimentos/cpf/{cpf}")
    @Transactional
    public void excluirRestricaoAlimentoPorCpf(@PathVariable String cpf){
        alimentoRepository.deletarPorCpf(cpf);
    }

    @DeleteMapping("/medicamentos/id/{id}")
    @Transactional
    public void excluirRestricaoMedicamentoPorId(@PathVariable Integer id){
        medicamentoRepository.deletarPorID(id);
    }

    @DeleteMapping("/medicamentos/cpf/{cpf}")
    @Transactional
    public void excluirRestricaoMedicamentoPorCpf(@PathVariable String cpf){
        medicamentoRepository.deletarPorCpf(cpf);
    }
}
