package br.edu.faculdade.vagas.dto;

import br.edu.faculdade.vagas.model.Pessoa;

/**
 * Frente 3 · o que a API DEVOLVE.
 *
 * NUNCA acrescentar aqui: senha, hash de senha, token, documento. Quando a
 * Pessoa ganhar senha, é este record que impede o vazamento — o que não existe
 * no contrato de saída não sai, mesmo que alguém esqueça.
 */
public record PessoaResposta(
        Long id,
        String nome,
        String email,
        String area,
        String senioridade
) {

    public static PessoaResposta de(Pessoa p) {
        return new PessoaResposta(p.id(), p.nome(), p.email(), p.area(), p.senioridade());
    }
}
