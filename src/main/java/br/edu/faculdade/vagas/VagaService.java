package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Onde a decisão mora — e, desde a aula 04, onde a tradução mora também:
 * VagaEntrada vira Vaga na hora de gravar, e Vaga vira VagaResposta na hora de
 * responder. O controller só fala HTTP; quem monta objeto de domínio é aqui.
 */
@Service
public class VagaService {

    private final VagaRepository repositorio;

    public VagaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<VagaResposta> listar() {
        return repositorio.todas().stream()
                .map(this::paraResposta)
                .toList();
    }

    public Optional<VagaResposta> buscarPorId(String id) {
        return repositorio.porId(id).map(this::paraResposta);
    }

    // Boolean.TRUE.equals: null, que é como "não respondeu" chega, vira false.
    public VagaResposta criar(VagaEntrada entrada) {
        Vaga nova = new Vaga(
                UUID.randomUUID().toString(),
                entrada.titulo(), entrada.descricao(), entrada.area(),
                entrada.senioridade(), entrada.local(),
                Boolean.TRUE.equals(entrada.aceitaIniciante()), entrada.empresaSlug());

        return paraResposta(repositorio.salvar(nova));
    }


    public Optional<VagaResposta> trocar(String id, VagaEntrada nova) {
        Vaga comId = new Vaga(
                id,
                nova.titulo(), nova.descricao(), nova.area(),
                nova.senioridade(), nova.local(),
                Boolean.TRUE.equals(nova.aceitaIniciante()), nova.empresaSlug());
        return repositorio.trocar(id, comId).map(this::paraResposta);
    }

    public boolean apagar(String id) {
        return repositorio.remover(id);
    }

    // Uma função só, usada por todos os métodos que devolvem vaga.
    private VagaResposta paraResposta(Vaga v) {
        return new VagaResposta(v.id(), v.titulo(), v.area(),
                v.senioridade(), v.local(), v.aceitaIniciante(), v.empresaSlug());
    }
}
