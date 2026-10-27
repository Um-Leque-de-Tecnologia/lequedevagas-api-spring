package br.edu.faculdade.vagas.controller;

import br.edu.faculdade.vagas.dto.PessoaEntrada;
import br.edu.faculdade.vagas.dto.PessoaResposta;
import br.edu.faculdade.vagas.service.PessoaService;
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

/** Frente 3 · o CRUD completo da pessoa. */
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService servico;

    public PessoaController(PessoaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<PessoaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResposta> porId(@PathVariable Long id) {
        return servico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PessoaResposta> criar(@Valid @RequestBody PessoaEntrada entrada) {
        PessoaResposta salva = servico.criar(entrada);
        URI onde = URI.create("/pessoas/" + salva.id());
        return ResponseEntity.created(onde).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResposta> trocar(@PathVariable Long id,
                                                 @Valid @RequestBody PessoaEntrada nova) {
        return servico.trocar(id, nova)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // O DELETE repetido devolve 204 e depois 404, e isso não quebra a
    // idempotência: ela é sobre o estado do servidor, não sobre a resposta.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        return servico.apagar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
