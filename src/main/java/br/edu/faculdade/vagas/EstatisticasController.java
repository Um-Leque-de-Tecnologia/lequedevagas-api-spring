package br.edu.faculdade.vagas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
