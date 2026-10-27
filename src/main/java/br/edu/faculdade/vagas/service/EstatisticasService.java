package br.edu.faculdade.vagas.service;

import br.edu.faculdade.vagas.model.Vaga;
import br.edu.faculdade.vagas.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Frente 4 · os números do catálogo. Quem conta, por enquanto, é o Java: um
 * select sem where e um groupingBy. Contar dentro do banco é a aula 08.
 */
@Service
public class EstatisticasService {

    private final VagaRepository vagas;

    public EstatisticasService(VagaRepository vagas) {
        this.vagas = vagas;
    }

    // LinkedHashMap, e não Map.of: as chaves saem na ordem em que foram
    // escritas, e a resposta não muda de forma a cada subida.
    public Map<String, Object> resumo() {
        List<Vaga> todas = vagas.findAll();
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalDeVagas", todas.size());
        resumo.put("aceitamIniciante", todas.stream().filter(Vaga::aceitaIniciante).count());
        resumo.put("porArea", todas.stream()
                .collect(Collectors.groupingBy(Vaga::area, Collectors.counting())));
        resumo.put("porSenioridade", todas.stream()
                .collect(Collectors.groupingBy(Vaga::senioridade, Collectors.counting())));
        return resumo;
    }
}
