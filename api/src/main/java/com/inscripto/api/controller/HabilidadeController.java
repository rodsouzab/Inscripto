package com.inscripto.api.controller;

import com.inscripto.api.dto.habilidade.AtualizacaoHabilidadeDTO;
import com.inscripto.api.dto.habilidade.CadastroHabilidadeDTO;
import com.inscripto.api.dto.habilidade.CadastroHabilidadeEncontreiroDTO;
import com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO;
import com.inscripto.api.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    @Autowired
    private final HabilidadeRepository habilidadeRepository;

    public HabilidadeController(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    @PostMapping
    public void criarHabilidade(@RequestBody CadastroHabilidadeDTO dto) {
        habilidadeRepository.criarHabilidade(dto.id(), dto.habilidade());
    }

    @GetMapping
    public ResponseEntity<List<ListagemHabilidadeDTO>> listarHabilidades() {
        return ResponseEntity.ok(habilidadeRepository.listarHabilidade());
    }

    @PutMapping
    public void atualizarHabilidade(@RequestBody AtualizacaoHabilidadeDTO dto) {
        habilidadeRepository.editarHabilidade(dto.id(), dto.habilidade());
    }

    @DeleteMapping("/{id}")
    public void excluirHabilidade(@PathVariable Integer id) {
        habilidadeRepository.deletarPorID(id);
    }

    @PostMapping("/vincular")
    public void vincularHabilidadesAoEncontreiro(@RequestBody List<CadastroHabilidadeEncontreiroDTO> dtos) {
    dtos.forEach(dto -> 
        habilidadeRepository.vincularHabilidadeAoEncontreiro(dto.cpfEncontreiro(), dto.idHabilidade())
    );
}


    @GetMapping("/encontreiro/{cpf}")
    public ResponseEntity<List<ListagemHabilidadeDTO>> listarHabilidadesPorEncontreiro(@PathVariable String cpf) {
        var lista = habilidadeRepository.listarHabilidadesPorEncontreiro(cpf);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/desvincular")
    public void removerHabilidadeDeEncontreiro(@RequestBody CadastroHabilidadeEncontreiroDTO dto) {
        habilidadeRepository.removerHabilidadeDeEncontreiro(dto.cpfEncontreiro(), dto.idHabilidade());
    }
}
