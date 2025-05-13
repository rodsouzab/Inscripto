package com.inscripto.api.controller;

import com.inscripto.api.dto.registroEncontreiro.CadastroRegistroEncontreiroDTO;
import com.inscripto.api.dto.registroEncontreiro.ChaveRegistroEncontreiroDTO;
import com.inscripto.api.model.Encontreiro;
import com.inscripto.api.model.Encontro;
import com.inscripto.api.model.Equipe;
import com.inscripto.api.model.RegistroEncontreiro;
import com.inscripto.api.repository.RegistroEncontreiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registroEncontreiro")
public class RegistroEncontreiroController {

    private final RegistroEncontreiroRepository registroEncontreiroRepository;

    @Autowired
    public RegistroEncontreiroController(RegistroEncontreiroRepository registroEncontreiroRepository) {
        this.registroEncontreiroRepository = registroEncontreiroRepository;
    }

    @PostMapping
    public ResponseEntity<Void> criarEncontro(@RequestBody CadastroRegistroEncontreiroDTO dto) {

        RegistroEncontreiro registro = new RegistroEncontreiro(
                dto.encontro(),
                dto.equipe(),
                dto.encontreiro()
        );
        registroEncontreiroRepository.inserirRegistroEncontreiro(registro);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<ChaveRegistroEncontreiroDTO>> listarRegistros() {
        List<ChaveRegistroEncontreiroDTO> registros = registroEncontreiroRepository.listarRegistros();
        return ResponseEntity.ok(registros);
    }

    @PutMapping("/equipe")
    public ResponseEntity<Void> atualizarEquipeRegistro(@RequestBody CadastroRegistroEncontreiroDTO dto) {
        RegistroEncontreiro registro = new RegistroEncontreiro(
                dto.encontro(),
                dto.equipe(),
                dto.encontreiro()
        );
        registroEncontreiroRepository.atualizarEquipeRegistro(registro);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/encontro")
    public ResponseEntity<Void> atualizarEncontroRegistro(@RequestBody CadastroRegistroEncontreiroDTO dto) {
        RegistroEncontreiro registro = new RegistroEncontreiro(
                dto.encontro(),
                dto.equipe(),
                dto.encontreiro()
        );
        registroEncontreiroRepository.atualizarEncontroRegistro(registro);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{anoEncontro}/{idEquipe}/{cpfEncontreiro}")
    public ResponseEntity<Void> removerRegistro(@PathVariable int anoEncontro, @PathVariable int idEquipe, @PathVariable String cpfEncontreiro) {

        Encontro encontro = new Encontro(anoEncontro);
        Equipe equipe = new Equipe(idEquipe);
        Encontreiro encontreiro = new Encontreiro(cpfEncontreiro);
        RegistroEncontreiro registro = new RegistroEncontreiro(encontro, equipe, encontreiro);
        registroEncontreiroRepository.removerRegistro(registro);
        return ResponseEntity.status(204).build();
    }


}
