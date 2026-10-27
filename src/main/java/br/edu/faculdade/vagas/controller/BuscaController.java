package br.edu.faculdade.vagas.controller;

import br.edu.faculdade.vagas.dto.VagaResposta;
import br.edu.faculdade.vagas.service.BuscaService;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Frente 4 · no mesmo caminho base do VagaController, em outro arquivo: duas
 * frentes no mesmo arquivo, na mesma semana, é conflito de merge na certa. O
 * Spring escolhe por especificidade, e /vagas/busca ganha de /vagas/{id}.
 */
@RestController
@RequestMapping("/vagas")
@Validated   // sem isto, os @Size abaixo são decoração
public class BuscaController {

    private final BuscaService servico;

    public BuscaController(BuscaService servico) {
        this.servico = servico;
    }

    // Zero resultados é 200 com lista vazia: você procurou, e o resultado é
    // zero. O oposto de pedir um id que não existe, que é 404.
    @GetMapping("/busca")
    public List<VagaResposta> buscar(
            @RequestParam(required = false)
            @Size(max = 60, message = "a área passa de 60 caracteres")
            String area,
            @RequestParam(required = false)
            @Size(max = 60, message = "a senioridade passa de 60 caracteres")
            String senioridade,
            @RequestParam(required = false)
            Boolean aceitaIniciante) {
        return servico.buscar(area, senioridade, aceitaIniciante);
    }
}
