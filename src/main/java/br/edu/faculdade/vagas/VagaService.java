package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

// A aula 03 acrescenta dois imports aqui:
// import java.util.Optional;
// import java.util.UUID;

/**
 * Onde mora a decisão. Hoje ela é pequena — buscar por id é uma decisão
 * simples — mas o lugar já está certo.
 *
 * Repare no que NÃO tem aqui: nenhum ResponseEntity, nenhum número de status.
 * O service não conhece HTTP. É essa fronteira que o deixa reaproveitável numa
 * tarefa agendada, num teste ou num programa de terminal, onde "404" não quer
 * dizer nada.
 */
@Service
public class VagaService {

    // O formato da injeção por construtor, que se repete nas quatro frentes:
    // 1. campo final, 2. recebe no construtor, 3. guarda. Nenhum `new` — quem
    // constrói é o contêiner do Spring.
    private final VagaRepository repositorio;

    public VagaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Vaga> listar() {
        return repositorio.todas();
    }

    /**
     * Por que devolver lista em vez de uma vaga só? Porque ainda não temos como
     * dizer "não achei" — isso é 404, e é a aula 03. Lista vazia é a resposta
     * honesta enquanto isso; devolver null seria a desonesta.
     *
     * É também o defeito combinado que a aula 03 conserta: hoje pedir uma vaga
     * que não existe devolve 200 com [].
     */
    public List<Vaga> buscarPorId(String id) {
        return repositorio.todas().stream()
                .filter(v -> v.id().equals(id))
                .toList();
    }

    // =========================================================================
    // AULA 03 · o service passa a saber dizer "não achei"
    //
    // buscarPorId deixa de devolver lista e passa a devolver Optional: vazio
    // quer dizer que o id não existe. Continua sem falar HTTP — quem traduz
    // isso para 404 é o controller.
    //
    // Para ativar: descomente o bloco abaixo e apague o buscarPorId da aula 02,
    // acima.
    // =========================================================================
    //
    // public Optional<Vaga> buscarPorId(String id) {
    //     return repositorio.porId(id);
    // }
    //
    // // O id nasce AQUI. Se viesse do cliente, dois clientes poderiam escolher
    // // o mesmo e um sobrescreveria o outro em silêncio.
    // public Vaga criar(Vaga nova) {
    //     Vaga comId = new Vaga(
    //         UUID.randomUUID().toString(),
    //         nova.titulo(), nova.descricao(), nova.area(),
    //         nova.senioridade(), nova.local(),
    //         nova.aceitaIniciante(), nova.empresaSlug());
    //     return repositorio.salvar(comId);
    // }
    //
    // // A ARMADILHA DO PUT: o id vem da URL, não do corpo. Usar nova.id() aqui
    // // gravaria null no lugar do id e você perderia o registro em vez de
    // // trocá-lo.
    // public Optional<Vaga> trocar(String id, Vaga nova) {
    //     Vaga comId = new Vaga(
    //         id,
    //         nova.titulo(), nova.descricao(), nova.area(),
    //         nova.senioridade(), nova.local(),
    //         nova.aceitaIniciante(), nova.empresaSlug());
    //     return repositorio.trocar(id, comId);
    // }
    //
    // public boolean apagar(String id) {
    //     return repositorio.remover(id);
    // }
}
