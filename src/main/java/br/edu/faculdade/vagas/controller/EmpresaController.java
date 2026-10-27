package br.edu.faculdade.vagas.controller;

import br.edu.faculdade.vagas.dto.EmpresaEntrada;
import br.edu.faculdade.vagas.dto.EmpresaResposta;
import br.edu.faculdade.vagas.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Frente 2 · o identificador na URL é o slug. Não há PUT: trocar o slug seria
 * trocar o endereço do recurso, e isso é decisão de contrato.
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService servico;

    public EmpresaController(EmpresaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<EmpresaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{slug}")
    public ResponseEntity<EmpresaResposta> porSlug(@PathVariable String slug) {
        return servico.buscarPorSlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // O Location aponta para o slug que O CLIENTE escolheu. Na frente 1 ele
    // aponta para um id que o banco inventou.
    @PostMapping
    public ResponseEntity<EmpresaResposta> criar(@Valid @RequestBody EmpresaEntrada entrada) {
        EmpresaResposta salva = servico.criar(entrada);
        URI onde = URI.create("/empresas/" + salva.slug());
        return ResponseEntity.created(onde).body(salva);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> apagar(@PathVariable String slug) {
        return servico.apagar(slug)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
