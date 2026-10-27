package br.edu.faculdade.vagas.controller;

import br.edu.faculdade.vagas.service.EstatisticasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Frente 4 · os números do catálogo. */
@RestController
public class EstatisticasController {

    private final EstatisticasService servico;

    public EstatisticasController(EstatisticasService servico) {
        this.servico = servico;
    }

    @GetMapping("/estatisticas")
    public Map<String, Object> resumo() {
        return servico.resumo();
    }
}
