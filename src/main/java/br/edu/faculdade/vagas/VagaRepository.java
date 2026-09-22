package br.edu.faculdade.vagas;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Sabe onde o dado está. Hoje é uma lista escrita à mão; na aula 05 vira uma
 * consulta ao banco, e nada fora desta classe muda quando isso acontecer.
 *
 * O repository guarda Vaga, o modelo — nunca VagaResposta. O contrato da API é
 * assunto do service para fora: quem grava não precisa saber o que o cliente vê.
 *
 * Sem construtor: é a única classe da frente 1 que não precisa de ninguém. Por
 * isso é a primeira a ser construída pelo Spring.
 */
@Repository
public class VagaRepository {

    /**
     * Doze vagas, quatro áreas, três senioridades, sete aceitando iniciante. Os
     * números não são enfeite: é isso que faz /estatisticas responder com graça
     * e a busca da aula 03 mostrar diferença. Lista uniforme esconde bug de
     * filtro.
     *
     * List.of(...) é IMUTÁVEL: envolver por ArrayList é o que torna este
     * repository escrevível, e foi a primeira mudança da aula 03.
     */
    private final List<Vaga> vagas = new ArrayList<>(List.of(
        new Vaga("1", "Pessoa Desenvolvedora Front-end Júnior",
            "Telas do produto em React e Next.js, pareando com gente mais experiente.",
            "Front-end", "Júnior", "Remoto", true, "aurora-tech"),
        new Vaga("2", "Estágio em Front-end",
            "Primeiro contato com HTML, CSS e JavaScript num time de agência.",
            "Front-end", "Estágio", "Presencial · Olinda", true, "mareh-digital"),
        new Vaga("3", "Pessoa Desenvolvedora Front-end Pleno",
            "Dono de features inteiras, do desenho à publicação.",
            "Front-end", "Pleno", "Remoto", false, "nuvem-rosa"),
        new Vaga("4", "Pessoa Desenvolvedora React Júnior",
            "Interface da plataforma de cursos, com foco em acessibilidade.",
            "Front-end", "Júnior", "Híbrido · Recife", true, "coral-labs"),
        new Vaga("5", "Pessoa Desenvolvedora Java Júnior",
            "APIs REST com Spring Boot, integrando transportadoras.",
            "Back-end", "Júnior", "Remoto", true, "tucano-systems"),
        new Vaga("6", "Pessoa Desenvolvedora Back-end Pleno",
            "Serviços de agendamento e faturamento, com fila e cache.",
            "Back-end", "Pleno", "Remoto", false, "aurora-tech"),
        new Vaga("7", "Pessoa Desenvolvedora Node Pleno",
            "Serviço de trilhas e progresso, em TypeScript.",
            "Back-end", "Pleno", "Híbrido · Recife", false, "coral-labs"),
        new Vaga("8", "Analista de Dados Júnior",
            "SQL, planilhas e construção de painéis para clientes do varejo.",
            "Dados", "Júnior", "Híbrido · Recife", true, "nuvem-rosa"),
        new Vaga("9", "Estágio em Dados",
            "Limpeza de base e primeiros painéis, com acompanhamento semanal.",
            "Dados", "Estágio", "Remoto", true, "nuvem-rosa"),
        new Vaga("10", "Pessoa Engenheira de Dados Pleno",
            "Pipelines de rastreamento de carga, em Python e SQL.",
            "Dados", "Pleno", "Remoto", false, "tucano-systems"),
        new Vaga("11", "Pessoa Desenvolvedora Mobile Júnior",
            "Aplicativos em React Native para clientes da agência.",
            "Mobile", "Júnior", "Remoto", true, "mareh-digital"),
        new Vaga("12", "Pessoa Desenvolvedora Android Júnior",
            "Aplicativo de agenda para as clínicas parceiras.",
            "Mobile", "Júnior", "Presencial · Recife", false, "aurora-tech")
    ));

    public List<Vaga> todas() {
        return List.copyOf(vagas);
    }

    public Optional<Vaga> porId(String id) {
        return vagas.stream().filter(v -> v.id().equals(id)).findFirst();
    }

    public Vaga salvar(Vaga nova) {
        vagas.add(nova);
        return nova;
    }

    public Optional<Vaga> trocar(String id, Vaga nova) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).id().equals(id)) {
                vagas.set(i, nova);
                return Optional.of(nova);
            }
        }
        return Optional.empty();
    }

    public boolean remover(String id) {
        return vagas.removeIf(v -> v.id().equals(id));
    }
}
