package br.edu.faculdade.vagas.controller;

import br.edu.faculdade.vagas.dto.VagaEntrada;
import br.edu.faculdade.vagas.dto.VagaResposta;
import br.edu.faculdade.vagas.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Frente 1 · recebe o pedido e devolve a resposta. Cada método tem uma linha
 * de decisão: nenhum if de regra, nenhum for. Se aparecer, é do service.
 *
 * Nada de Vaga aqui: o que entra é VagaEntrada, o que sai é VagaResposta.
 */
@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService servico;

    public VagaController(VagaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<VagaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaResposta> porId(@PathVariable Long id) {
        return servico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VagaResposta> criar(@Valid @RequestBody VagaEntrada entrada) {
        VagaResposta salva = servico.criar(entrada);
        URI onde = URI.create("/vagas/" + salva.id());
        return ResponseEntity.created(onde).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaResposta> trocar(@PathVariable Long id,
                                               @Valid @RequestBody VagaEntrada nova) {
        return servico.trocar(id, nova)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ResponseEntity<Void>: não há corpo para tipar. Devolver o objeto
    // recém-apagado sugeriria que ele ainda está lá.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        return servico.apagar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
