package com.inscripto.api.controller;


import com.inscripto.api.dto.habilidade.AtualizacaoHabilidadeDTO;
import com.inscripto.api.dto.habilidade.CadastroHabilidadeDTO;
import com.inscripto.api.dto.habilidade.ListagemHabilidadeDTO;
import com.inscripto.api.model.Habilidade;
import com.inscripto.api.repository.HabilidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    private final HabilidadeRepository habilidadeRepository;

    public HabilidadeController(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    @PostMapping
    @Transactional
    public void criarHabilidade(@RequestBody CadastroHabilidadeDTO dto){
        habilidadeRepository.criarHabilidade(dto.id(), dto.habilidade());
    }

    @GetMapping
    public Page<ListagemHabilidadeDTO> listarHabilidades(@PageableDefault(sort = {"habilidade"}, size = 10) Pageable paginacao){
        return habilidadeRepository.listarHabilidade(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizarHabilidade(@RequestBody AtualizacaoHabilidadeDTO dto){
        habilidadeRepository.editarHabilidade(dto.id(), dto.habilidade());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirHabilidade(@PathVariable Integer id){
        habilidadeRepository.deletarPorID(id);
    }
}
