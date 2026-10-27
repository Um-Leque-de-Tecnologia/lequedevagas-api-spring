package br.edu.faculdade.vagas.service;

import br.edu.faculdade.vagas.dto.VagaResposta;
import br.edu.faculdade.vagas.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Frente 4 · a única sem recurso próprio: ela consulta o que a frente 1 guarda.
 * O repository da frente 1, e não o service dela — regra não chama regra.
 *
 * O filtro ainda é Java: o findAll() traz a tabela inteira e o stream joga
 * fora o que não serve. A aula 05 mediu isso (12 linhas para devolver 3), e a
 * aula 08 leva o filtro para dentro do banco.
 */
@Service
public class BuscaService {

    private final VagaRepository repositorio;

    public BuscaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Boolean, e não boolean: null é como "o filtro não foi enviado" chega.
    public List<VagaResposta> buscar(String area, String senioridade, Boolean aceitaIniciante) {
        return repositorio.findAll().stream()
                .filter(v -> area == null || v.area().equalsIgnoreCase(area))
                .filter(v -> senioridade == null || v.senioridade().equalsIgnoreCase(senioridade))
                .filter(v -> aceitaIniciante == null || v.aceitaIniciante() == aceitaIniciante)
                .map(VagaResposta::de)
                .toList();
    }
}
