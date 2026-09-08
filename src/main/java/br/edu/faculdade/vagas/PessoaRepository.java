package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.List;

// A aula 03 acrescenta dois imports aqui:
// import java.util.ArrayList;
// import java.util.Optional;

@Repository
public class PessoaRepository {

    public List<Pessoa> todas() {
        return List.of(
            new Pessoa("1", "Ana Nogueira", "ana@exemplo.com", "Front-end", "Júnior"),
            new Pessoa("2", "Bruno Sales", "bruno@exemplo.com", "Back-end", "Júnior"),
            new Pessoa("3", "Carla Menezes", "carla@exemplo.com", "Dados", "Estágio"),
            new Pessoa("4", "Diego Tavares", "diego@exemplo.com", "Front-end", "Pleno"),
            new Pessoa("5", "Elisa Prado", "elisa@exemplo.com", "Mobile", "Júnior"),
            new Pessoa("6", "Fábio Vasconcelos", "fabio@exemplo.com", "Back-end", "Pleno"));
    }

    // =========================================================================
    // AULA 03 · o repository da frente 3, igual ao da frente 1
    //
    // Para ativar: descomente o bloco abaixo, mova as 6 pessoas para dentro do
    // campo e apague o método todas() da aula 02, acima.
    // =========================================================================
    //
    // private final List<Pessoa> pessoas = new ArrayList<>(List.of(
    //     as mesmas 6 pessoas que hoje estão dentro de todas()
    // ));
    //
    // public List<Pessoa> todas() {
    //     return List.copyOf(pessoas);
    // }
    //
    // public Optional<Pessoa> porId(String id) {
    //     return pessoas.stream().filter(p -> p.id().equals(id)).findFirst();
    // }
    //
    // public Pessoa salvar(Pessoa nova) {
    //     pessoas.add(nova);
    //     return nova;
    // }
    //
    // public Optional<Pessoa> trocar(String id, Pessoa nova) {
    //     for (int i = 0; i < pessoas.size(); i++) {
    //         if (pessoas.get(i).id().equals(id)) {
    //             pessoas.set(i, nova);
    //             return Optional.of(nova);
    //         }
    //     }
    //     return Optional.empty();
    // }
    //
    // public boolean remover(String id) {
    //     return pessoas.removeIf(p -> p.id().equals(id));
    // }
}
