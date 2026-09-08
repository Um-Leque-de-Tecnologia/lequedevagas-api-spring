package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.List;

// A aula 03 acrescenta dois imports aqui:
// import java.util.ArrayList;
// import java.util.Optional;

/**
 * Mesma forma do VagaRepository, com uma diferença: aqui se busca por slug.
 *
 * Os cinco slugs precisam bater, letra por letra, com o empresaSlug das vagas.
 * Um erro de digitação aqui só aparece na aula 06, quando a junção não achar a
 * empresa — longe de onde foi criado.
 */
@Repository
public class EmpresaRepository {

    public List<Empresa> todas() {
        return List.of(
            new Empresa("1", "Aurora Tech", "aurora-tech",
                "aurora.tech", "Produto de gestão para clínicas. Time pequeno, remoto desde 2020."),
            new Empresa("2", "Nuvem Rosa", "nuvem-rosa",
                "nuvemrosa.com.br", "Consultoria de dados para o varejo do Nordeste."),
            new Empresa("3", "Mareh Digital", "mareh-digital",
                "mareh.digital", "Agência de produtos digitais em Olinda."),
            new Empresa("4", "Coral Labs", "coral-labs",
                "corallabs.io", "Plataforma de educação corporativa."),
            new Empresa("5", "Tucano Systems", "tucano-systems",
                "tucano.sys.br", "Integrações e APIs para logística."));
    }

    // =========================================================================
    // AULA 03 · o mesmo que a frente 1 faz, buscando por slug
    //
    // Para ativar: descomente o bloco abaixo, mova as 5 empresas para dentro do
    // campo e apague o método todas() da aula 02, acima.
    // =========================================================================
    //
    // private final List<Empresa> empresas = new ArrayList<>(List.of(
    //     as mesmas 5 empresas que hoje estão dentro de todas()
    // ));
    //
    // public List<Empresa> todas() {
    //     return List.copyOf(empresas);
    // }
    //
    // public Optional<Empresa> porSlug(String slug) {
    //     return empresas.stream().filter(e -> e.slug().equals(slug)).findFirst();
    // }
    //
    // public Empresa salvar(Empresa nova) {
    //     empresas.add(nova);
    //     return nova;
    // }
    //
    // public boolean remover(String slug) {
    //     return empresas.removeIf(e -> e.slug().equals(slug));
    // }
}
