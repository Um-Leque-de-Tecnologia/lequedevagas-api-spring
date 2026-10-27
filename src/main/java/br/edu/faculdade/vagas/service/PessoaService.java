package br.edu.faculdade.vagas.service;

import br.edu.faculdade.vagas.dto.PessoaEntrada;
import br.edu.faculdade.vagas.dto.PessoaResposta;
import br.edu.faculdade.vagas.model.Pessoa;
import br.edu.faculdade.vagas.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Frente 3 · o CRUD completo da pessoa. */
@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<PessoaResposta> listar() {
        return repositorio.findAll().stream()
                .map(PessoaResposta::de)
                .toList();
    }

    public Optional<PessoaResposta> buscarPorId(Long id) {
        return repositorio.findById(id).map(PessoaResposta::de);
    }

    // Nenhuma checagem de e-mail repetido aqui, de propósito: o unique da
    // coluna recusa, e o TratadorDeErros transforma a recusa em 409.
    public PessoaResposta criar(PessoaEntrada entrada) {
        Pessoa nova = new Pessoa(
                entrada.nome(), entrada.email(), entrada.area(), entrada.senioridade());
        return PessoaResposta.de(repositorio.save(nova));
    }

    public Optional<PessoaResposta> trocar(Long id, PessoaEntrada nova) {
        return repositorio.findById(id).map(existente -> {
            existente.trocarDados(nova.nome(), nova.email(), nova.area(), nova.senioridade());
            return PessoaResposta.de(repositorio.save(existente));
        });
    }

    public boolean apagar(Long id) {
        if (!repositorio.existsById(id)) {
            return false;
        }
        repositorio.deleteById(id);
        return true;
    }
}
