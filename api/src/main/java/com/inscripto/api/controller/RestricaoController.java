package com.inscripto.api.controller;

import com.inscripto.api.dto.restricao.*;
import com.inscripto.api.repository.RestricaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restricoes")
public class RestricaoController {

    private final RestricaoRepository restricaoRepository;

    public RestricaoController(RestricaoRepository restricaoRepository) {
        this.restricaoRepository = restricaoRepository;
    }

    @PostMapping("/alimentos")
    public void criarRestricaoAlimento(@RequestBody CadastroRestricaoAlimentoDTO dto) {
        restricaoRepository.criarRestricaoAlimento(dto.cpf(), dto.alimento());
    }

    @PostMapping("/medicamentos")
    public void criarRestricaoMedicamentos(@RequestBody CadastroRestricaoMedicamentoDTO dto) {
        restricaoRepository.criarRestricaoMedicamento(dto.cpf(), dto.medicamento());
    }

    @GetMapping("/alimentos")
    public List<ListagemRestricaoAlimentoDTO> listarRestricaoAlimento() {
        return restricaoRepository.listarRestricaoAlimento();
    }

    @GetMapping("/medicamentos")
    public List<ListagemRestricaoMedicamentoDTO> listarRestricaoMedicamento() {
        return restricaoRepository.listarRestricaoMedicamento();
    }

    @PutMapping("/alimentos")
    public void atualizarRestricaoAlimento(@RequestBody AtualizacaoRestricaoAlimentoDTO dto) {
        restricaoRepository.atualizarRestricaoAlimento(dto.id(), dto.alimento());
    }

    @PutMapping("/medicamentos")
    public void atualizarRestricaoMedicamento(@RequestBody AtualizacaoRestricaoMedicamentoDTO dto) {
        restricaoRepository.atualizarRestricaoMedicamento(dto.id(), dto.medicamento());
    }

    @DeleteMapping("/alimentos/id/{id}")
    public void excluirRestricaoAlimentoPorId(@PathVariable Integer id) {
        restricaoRepository.excluirRestricaoAlimentoPorId(id);
    }

    @DeleteMapping("/alimentos/cpf/{cpf}")
    public void excluirRestricaoAlimentoPorCpf(@PathVariable String cpf) {
        restricaoRepository.excluirRestricaoAlimentoPorCpf(cpf);
    }

    @DeleteMapping("/medicamentos/id/{id}")
    public void excluirRestricaoMedicamentoPorId(@PathVariable Integer id) {
        restricaoRepository.excluirRestricaoMedicamentoPorId(id);
    }

    @DeleteMapping("/medicamentos/cpf/{cpf}")
    public void excluirRestricaoMedicamentoPorCpf(@PathVariable String cpf) {
        restricaoRepository.excluirRestricaoMedicamentoPorCpf(cpf);
    }
}
