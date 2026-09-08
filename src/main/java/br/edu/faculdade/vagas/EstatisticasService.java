package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A entrega da aula 02, intacta. Evoluir a API não pode quebrar o que já
 * existia — conferir isso faz parte de juntar as quatro partes da aula 03.
 */
@Service
public class EstatisticasService {

    private final VagaRepository vagas;

    public EstatisticasService(VagaRepository vagas) {
        this.vagas = vagas;
    }

    // Map.of aceita no máximo 10 pares; são quatro aqui, então cabe. Se a
    // equipe quiser mais números, troque por new LinkedHashMap<>() — e de
    // quebra a ordem das chaves passa a ser a que você escreveu.
    public Map<String, Object> resumo() {
        List<Vaga> todas = vagas.todas();
        return Map.of(
            "totalDeVagas", todas.size(),
            "aceitamIniciante", todas.stream()
                .filter(Vaga::aceitaIniciante)
                .count(),
            "porArea", todas.stream()
                .collect(Collectors.groupingBy(Vaga::area, Collectors.counting())),
            "porSenioridade", todas.stream()
                .collect(Collectors.groupingBy(Vaga::senioridade, Collectors.counting()))
        );
    }
}
