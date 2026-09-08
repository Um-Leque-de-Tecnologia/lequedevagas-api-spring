package br.edu.faculdade.vagas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// A aula 03 acrescenta estes imports aqui:
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import java.net.URI;

@RestController
public class EmpresaController {

    private final EmpresaService servico;

    public EmpresaController(EmpresaService servico) {
        this.servico = servico;
    }

    @GetMapping("/empresas")
    public List<Empresa> listar() {
        return servico.listar();
    }

    @GetMapping("/empresas/{slug}")
    public List<Empresa> porSlug(@PathVariable String slug) {
        return servico.buscarPorSlug(slug);
    }

    // =========================================================================
    // AULA 03 · o mesmo 201, com o Location apontando para outro lado
    //
    // Para ativar: ponha @RequestMapping("/empresas") na classe, descomente o
    // bloco abaixo e apague os dois métodos da aula 02, acima.
    // =========================================================================
    //
    // @GetMapping
    // public List<Empresa> listar() {
    //     return servico.listar();
    // }
    //
    // // Mesma tradução da frente 1, em outro recurso: toda rota que busca uma
    // // coisa precisa saber dizer que ela não existe.
    // @GetMapping("/{slug}")
    // public ResponseEntity<Empresa> porSlug(@PathVariable String slug) {
    //     return servico.buscarPorSlug(slug)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());
    // }
    //
    // @PostMapping
    // public ResponseEntity<Empresa> criar(@RequestBody Empresa nova) {
    //     Empresa salva = servico.criar(nova);
    //     // Na frente 1 o Location aponta para um id que a API acabou de
    //     // inventar. Aqui aponta para o slug que O CLIENTE escolheu. Mesmo
    //     // 201, responsabilidade sobre o endereço em lados opostos — e isso é
    //     // uma decisão de projeto de API, não um detalhe.
    //     URI onde = URI.create("/empresas/" + salva.slug());
    //     return ResponseEntity.created(onde).body(salva);
    // }
    //
    // @DeleteMapping("/{slug}")
    // public ResponseEntity<Void> apagar(@PathVariable String slug) {
    //     return servico.apagar(slug)
    //         ? ResponseEntity.noContent().build()
    //         : ResponseEntity.notFound().build();
    // }
}
