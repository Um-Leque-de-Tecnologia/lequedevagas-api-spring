package br.edu.faculdade.vagas.service;

import br.edu.faculdade.vagas.dto.EmpresaEntrada;
import br.edu.faculdade.vagas.dto.EmpresaResposta;
import br.edu.faculdade.vagas.exception.RecursoJaExiste;
import br.edu.faculdade.vagas.model.Empresa;
import br.edu.faculdade.vagas.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Frente 2 · a empresa se busca pelo slug, que é o que vai na URL. */
@Service
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<EmpresaResposta> listar() {
        return repositorio.findAll().stream()
                .map(EmpresaResposta::de)
                .toList();
    }

    public Optional<EmpresaResposta> buscarPorSlug(String slug) {
        return repositorio.findBySlug(slug).map(EmpresaResposta::de);
    }

    public EmpresaResposta criar(EmpresaEntrada entrada) {
        // A checagem devolve um 409 com mensagem. O unique da coluna é a rede
        // embaixo dela: vale também para o console, a carga e dois POST juntos.
        if (repositorio.findBySlug(entrada.slug()).isPresent()) {
            throw new RecursoJaExiste("Empresa", entrada.slug());
        }
        Empresa nova = new Empresa(
                entrada.nome(), entrada.slug(), entrada.site(), entrada.descricao());
        return EmpresaResposta.de(repositorio.save(nova));
    }

    public boolean apagar(String slug) {
        return repositorio.findBySlug(slug)
                .map(e -> {
                    repositorio.delete(e);
                    return true;
                })
                .orElse(false);
    }
}
