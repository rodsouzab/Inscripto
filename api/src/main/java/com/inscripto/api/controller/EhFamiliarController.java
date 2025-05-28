package com.inscripto.api.controller;

import com.inscripto.api.model.EhFamiliar;
import com.inscripto.api.repository.EhFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encontreiros/familiares")
public class EhFamiliarController {

    private final EhFamiliarRepository ehFamiliarRepository;

    @Autowired
    public EhFamiliarController(EhFamiliarRepository ehFamiliarRepository) {
        this.ehFamiliarRepository = ehFamiliarRepository;
    }

    @PostMapping
public ResponseEntity<List<EhFamiliar>> createEhFamiliar(@RequestBody List<EhFamiliar> ehFamiliar) {
    List<EhFamiliar> savedList = ehFamiliarRepository.saveAll(ehFamiliar);
    return ResponseEntity.ok(savedList);
}


    @GetMapping
    public ResponseEntity<List<EhFamiliar>> getAllEhFamiliares() {
        List<EhFamiliar> ehFamiliares = ehFamiliarRepository.findAll();
        return ResponseEntity.ok(ehFamiliares);
    }

    @PutMapping("/{cpfEncontreiro1}/{cpfEncontreiro2}")
    public ResponseEntity<String> atualizarEhFamiliar(
            @PathVariable String cpfEncontreiro1,
            @PathVariable String cpfEncontreiro2,
            @RequestBody EhFamiliar ehFamiliarAtualizado) {
        if (ehFamiliarAtualizado.getEncontreiro1() == null) {
            ehFamiliarAtualizado.setEncontreiro1(new com.inscripto.api.model.Encontreiro());
        }
        if (ehFamiliarAtualizado.getEncontreiro2() == null) {
            ehFamiliarAtualizado.setEncontreiro2(new com.inscripto.api.model.Encontreiro());
        }
        ehFamiliarAtualizado.getEncontreiro1().setCpf(cpfEncontreiro1);
        ehFamiliarAtualizado.getEncontreiro2().setCpf(cpfEncontreiro2);
        ehFamiliarRepository.atualizarEhFamiliar(ehFamiliarAtualizado);
        return ResponseEntity.ok("EhFamiliar atualizado com sucesso!");
    }

    @DeleteMapping("/{cpfEncontreiro1}/{cpfEncontreiro2}")
    public ResponseEntity<Void> deleteEhFamiliar(
            @PathVariable String cpfEncontreiro1,
            @PathVariable String cpfEncontreiro2) {
        ehFamiliarRepository.deleteByCpf(cpfEncontreiro1, cpfEncontreiro2);
        return ResponseEntity.noContent().build();
    }

}
