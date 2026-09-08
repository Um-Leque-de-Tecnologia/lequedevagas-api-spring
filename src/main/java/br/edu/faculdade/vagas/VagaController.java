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

/**
 * Recebe o pedido e devolve a resposta. Cada método tem uma linha.
 *
 * Leia o método em voz alta: "devolve as vagas". Nenhum if, nenhum for, nenhum
 * número solto. Se aparecer algum, é regra — e regra é do servico.
 */
@RestController
public class VagaController {

    private final VagaService servico;

    public VagaController(VagaService servico) {
        this.servico = servico;
    }

    @GetMapping("/vagas")
    public List<Vaga> listar() {
        return servico.listar();
    }

    // Devolve lista, e por isso responde 200 mesmo para um id que não existe.
    // É o defeito que a aula 03 conserta.
    @GetMapping("/vagas/{id}")
    public List<Vaga> porId(@PathVariable String id) {
        return servico.buscarPorId(id);
    }

    // =========================================================================
    // AULA 03 · é aqui, e só aqui, que se fala HTTP
    //
    // O controller ganha ResponseEntity e escolhe o status: 200 ou 404 no GET
    // de item, 201 com cabeçalho Location no POST, 204 sem corpo no DELETE.
    //
    // Para ativar: ponha @RequestMapping("/vagas") na classe, descomente o
    // bloco abaixo e apague os dois métodos da aula 02, acima. Com o
    // @RequestMapping na classe, cada anotação carrega só o que vem depois de
    // /vagas.
    // =========================================================================
    //
    // // Lista sempre existe, mesmo vazia — 200 direto, sem ResponseEntity.
    // @GetMapping
    // public List<Vaga> listar() {
    //     return servico.listar();
    // }
    //
    // // map roda se achou, orElse se não achou. O `if` está aí dentro — e o
    // // compilador não deixa você esquecer o segundo caso, que é o bug mais
    // // comum quando o if é escrito à mão.
    // @GetMapping("/{id}")
    // public ResponseEntity<Vaga> porId(@PathVariable String id) {
    //     return servico.buscarPorId(id)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
    //
    // // 201 + Location: criei, e está bem ali. Quem só precisa do endereço não
    // // tem que vasculhar o corpo atrás do id e montar a URL na mão.
    // @PostMapping
    // public ResponseEntity<Vaga> criar(@RequestBody Vaga nova) {
    //     Vaga salva = servico.criar(nova);
    //     URI onde = URI.create("/vagas/" + salva.id());
    //     return ResponseEntity.created(onde).body(salva);
    // }
    //
    // @PutMapping("/{id}")
    // public ResponseEntity<Vaga> trocar(@PathVariable String id,
    //                                    @RequestBody Vaga nova) {
    //     return servico.trocar(id, nova)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
    //
    // // ResponseEntity<Void>: não há corpo nenhum para tipar. Devolver o objeto
    // // recém-apagado sugeriria que ele ainda está lá.
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> apagar(@PathVariable String id) {
    //     return servico.apagar(id)
    //         ? ResponseEntity.noContent().build()
    //         : ResponseEntity.notFound().build();
    // }
}
