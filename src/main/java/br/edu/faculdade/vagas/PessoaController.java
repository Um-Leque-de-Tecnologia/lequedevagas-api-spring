package br.edu.faculdade.vagas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// A aula 03 acrescenta estes imports aqui:
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import java.net.URI;

@RestController
public class PessoaController {

    private final PessoaService servico;

    public PessoaController(PessoaService servico) {
        this.servico = servico;
    }

    @GetMapping("/pessoas")
    public List<Pessoa> listar() {
        return servico.listar();
    }

    @GetMapping("/pessoas/{id}")
    public List<Pessoa> porId(@PathVariable String id) {
        return servico.buscarPorId(id);
    }

    // =========================================================================
    // AULA 03 · o CRUD completo da frente 3
    //
    // Para ativar: ponha @RequestMapping("/pessoas") na classe, descomente o
    // bloco abaixo e apague os dois métodos da aula 02, acima.
    // =========================================================================
    //
    // @GetMapping
    // public List<Pessoa> listar() {
    //     return servico.listar();
    // }
    //
    // @GetMapping("/{id}")
    // public ResponseEntity<Pessoa> porId(@PathVariable String id) {
    //     return servico.buscarPorId(id)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
    //
    // @PostMapping
    // public ResponseEntity<Pessoa> criar(@RequestBody Pessoa nova) {
    //     Pessoa salva = servico.criar(nova);
    //     URI onde = URI.create("/pessoas/" + salva.id());
    //     return ResponseEntity.created(onde).body(salva);
    // }
    //
    // @PutMapping("/{id}")
    // public ResponseEntity<Pessoa> trocar(@PathVariable String id,
    //                                      @RequestBody Pessoa nova) {
    //     return servico.trocar(id, nova)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
    //
    // // O DELETE repetido devolve 204 e depois 404, e isso NÃO quebra a
    // // idempotência: ela é sobre o estado do servidor, não sobre a resposta.
    // // Depois de uma ou de dez chamadas, aquela pessoa não existe.
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> apagar(@PathVariable String id) {
    //     return servico.apagar(id)
    //         ? ResponseEntity.noContent().build()
    //         : ResponseEntity.notFound().build();
    // }
}
