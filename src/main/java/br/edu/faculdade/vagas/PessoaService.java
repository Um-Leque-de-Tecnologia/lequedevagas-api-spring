package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

// A aula 03 acrescenta dois imports aqui:
// import java.util.Optional;
// import java.util.UUID;

/**
 * Idêntico ao EmpresaService, trocando Empresa por Pessoa e slug por id. A
 * esta altura o formato já se escreve de cabeça — é o ponto do desafio da
 * aula 02 ter quatro partes com a mesma forma.
 */
@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Pessoa> listar() {
        return repositorio.todas();
    }

    public List<Pessoa> buscarPorId(String id) {
        return repositorio.todas().stream()
                .filter(p -> p.id().equals(id))
                .toList();
    }

    // =========================================================================
    // AULA 03 · o CRUD da frente 3, e a prova da idempotência
    //
    // A entrega diferente desta frente não é código: é o idempotencia.http na
    // raiz do repositório, que registra POST, PUT e DELETE repetidos com o
    // status de cada chamada anotado.
    //
    // Para ativar: descomente o bloco abaixo e apague o buscarPorId da aula 02,
    // acima.
    // =========================================================================
    //
    // public Optional<Pessoa> buscarPorId(String id) {
    //     return repositorio.porId(id);
    // }
    //
    // // Repetir este método cria uma segunda pessoa, com outro id. É isso que
    // // "POST não é idempotente" quer dizer — e o motivo de site bem-feito
    // // desabilitar o botão depois do clique.
    // public Pessoa criar(Pessoa nova) {
    //     Pessoa comId = new Pessoa(
    //         UUID.randomUUID().toString(),
    //         nova.nome(), nova.email(), nova.area(), nova.senioridade());
    //     return repositorio.salvar(comId);
    // }
    //
    // // O id vem da URL, não do corpo. Repetir esta chamada não muda mais
    // // nada: o estado final já é o pedido.
    // public Optional<Pessoa> trocar(String id, Pessoa nova) {
    //     Pessoa comId = new Pessoa(
    //         id,
    //         nova.nome(), nova.email(), nova.area(), nova.senioridade());
    //     return repositorio.trocar(id, comId);
    // }
    //
    // public boolean apagar(String id) {
    //     return repositorio.remover(id);
    // }
}
