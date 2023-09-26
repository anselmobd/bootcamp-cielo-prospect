package com.example.prospect.controller;

import com.example.prospect.service.PropectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Prospect", description = "APIs de envio e verificação de Feedbacks")
@RestController
@RequestMapping("/api/v1")
public class ProspectController {

    private final PropectService propectService;

    @Autowired
    public ProspectController(PropectService propectService) {
        this.propectService = propectService;
    }

    @Operation(
            summary = "Pessoa física",
            description = "Teste.")
    @GetMapping("/pessoa_fisica")
    public String pessoaFisica() {
        return this.propectService.pessoaFisica();
    }

}
