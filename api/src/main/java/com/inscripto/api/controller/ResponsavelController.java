package com.inscripto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.inscripto.api.repository.ResponsavelRepository;
import com.inscripto.api.model.Responsavel;
import java.util.List;

@RestController
public class ResponsavelController {
    
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping("/responsavel")
    Responsavel newResponsavel(@RequestBody Responsavel newResponsavel) {
        return responsavelRepository.save(newResponsavel);
    }

    @GetMapping
    public List<Responsavel> getAllResponsaveis() {
        return responsavelRepository.findAll();
    }



}
