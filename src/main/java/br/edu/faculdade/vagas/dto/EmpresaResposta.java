package br.edu.faculdade.vagas.dto;

import br.edu.faculdade.vagas.model.Empresa;

/** Frente 2 · o que a API DEVOLVE. */
public record EmpresaResposta(
        Long id,
        String nome,
        String slug,
        String site,
        String descricao
) {

    public static EmpresaResposta de(Empresa e) {
        return new EmpresaResposta(e.id(), e.nome(), e.slug(), e.site(), e.descricao());
    }
}
