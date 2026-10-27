package br.edu.faculdade.vagas.service;

import br.edu.faculdade.vagas.dto.VagaEntrada;
import br.edu.faculdade.vagas.dto.VagaResposta;
import br.edu.faculdade.vagas.model.Vaga;
import br.edu.faculdade.vagas.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Frente 1 · onde a decisão mora, e onde a tradução mora também: VagaEntrada
 * vira Vaga na hora de gravar, e Vaga vira VagaResposta (pelo VagaResposta.de)
 * na hora de responder.
 */
@Service
public class VagaService {

    private final VagaRepository repositorio;

    public VagaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<VagaResposta> listar() {
        return repositorio.findAll().stream()
                .map(VagaResposta::de)
                .toList();
    }

    public Optional<VagaResposta> buscarPorId(Long id) {
        return repositorio.findById(id).map(VagaResposta::de);
    }

    // A Vaga nasce com id null, e o save devolve a mesma vaga já numerada.
    public VagaResposta criar(VagaEntrada entrada) {
        Vaga nova = new Vaga(
                entrada.titulo(), entrada.descricao(), entrada.area(),
                entrada.senioridade(), entrada.local(),
                Boolean.TRUE.equals(entrada.aceitaIniciante()), entrada.empresaSlug());
        return VagaResposta.de(repositorio.save(nova));
    }

    // Busca, troca os dados e salva. Id que não existe: Optional vazio, 404.
    public Optional<VagaResposta> trocar(Long id, VagaEntrada nova) {
        return repositorio.findById(id).map(existente -> {
            existente.trocarDados(
                    nova.titulo(), nova.descricao(), nova.area(),
                    nova.senioridade(), nova.local(),
                    Boolean.TRUE.equals(nova.aceitaIniciante()), nova.empresaSlug());
            return VagaResposta.de(repositorio.save(existente));
        });
    }

    // deleteById não avisa quando o id não existe. Quem precisa distinguir
    // 204 de 404 pergunta antes.
    public boolean apagar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }
}
