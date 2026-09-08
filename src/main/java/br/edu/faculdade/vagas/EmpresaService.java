package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;

// A aula 03 acrescenta dois imports aqui:
// import java.util.Optional;
// import java.util.UUID;

/**
 * O mesmo formato do VagaService, com uma diferença que é da frente 2: empresa
 * se busca por slug, não por id — é o slug que vai na URL.
 */
@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empresa> listar() {
        return repositorio.todas();
    }

    // Lista, pelo mesmo motivo da frente 1: ainda não há como dizer "não achei".
    public List<Empresa> buscarPorSlug(String slug) {
        return repositorio.todas().stream()
                .filter(e -> e.slug().equals(slug))
                .toList();
    }

    // =========================================================================
    // AULA 03 · a frente 2 e o identificador que vem de fora
    //
    // A frente 1 inventa o identificador da vaga. Aqui não: o slug vem no
    // corpo, escolhido por quem cria, e a API só o guarda. O que a API gera é o
    // id — que nesta frente não identifica coisa nenhuma nas URLs.
    //
    // Não há PUT de empresa nesta aula: trocar o slug seria trocar o endereço
    // do recurso, e isso é uma decisão de contrato que a turma ainda não tem
    // ferramenta para tomar.
    //
    // Para ativar: descomente o bloco abaixo e apague o buscarPorSlug da aula
    // 02, acima.
    // =========================================================================
    //
    // public Optional<Empresa> buscarPorSlug(String slug) {
    //     return repositorio.porSlug(slug);
    // }
    //
    // public Empresa criar(Empresa nova) {
    //     Empresa comId = new Empresa(
    //         UUID.randomUUID().toString(),
    //         nova.nome(), nova.slug(), nova.site(), nova.descricao());
    //     return repositorio.salvar(comId);
    // }
    //
    // public boolean apagar(String slug) {
    //     return repositorio.remover(slug);
    // }
}
