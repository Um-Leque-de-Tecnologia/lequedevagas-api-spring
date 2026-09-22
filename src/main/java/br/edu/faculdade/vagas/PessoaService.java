package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// A aula 03 acrescenta dois imports aqui:
// import java.util.Optional;
// import java.util.UUID;

/**
 * Idêntico ao EmpresaService, trocando Empresa por Pessoa e slug por id. A
 * esta altura o formato já se escreve de cabeça — é o ponto do desafio da
 * aula 02 ter quatro partes com a mesma forma.
 */
@Service
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Pessoa> listar() {
        return repositorio.todas();
    }

    public List<Pessoa> buscarPorId(String id) {
        return repositorio.todas().stream()
                .filter(p -> p.id().equals(id))
                .toList();
    }

     public Pessoa criar(Pessoa nova) {
         Pessoa comId = new Pessoa(
             UUID.randomUUID().toString(),
             nova.nome(), nova.email(), nova.area(), nova.senioridade());
         return repositorio.salvar(comId);
     }

     // O id vem da URL, não do corpo. Repetir esta chamada não muda mais
     // nada: o estado final já é o pedido.
     public Optional<Pessoa> trocar(String id, Pessoa nova) {
         Pessoa comId = new Pessoa(
             id,
             nova.nome(), nova.email(), nova.area(), nova.senioridade());
         return repositorio.trocar(id, comId);
     }

     public boolean apagar(String id) {
         return repositorio.remover(id);
     }
}
