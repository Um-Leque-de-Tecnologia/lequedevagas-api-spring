package br.edu.faculdade.vagas.dto;

import br.edu.faculdade.vagas.model.Vaga;

/**
 * Frente 1 · o que a API DEVOLVE.
 *
 * Tem id, porque o cliente precisa dele para chamar /vagas/{id} depois. E não
 * tem descricao: a listagem não precisa do texto longo, e a saída poder
 * esconder campo é o ponto de separar entrada, saída e modelo.
 *
 * Quem traduz Vaga em VagaResposta é o de(), num lugar só: o VagaService e o
 * BuscaService devolvem o mesmo contrato, e nenhum dos dois o monta à mão.
 */
public record VagaResposta(
        Long id,
        String titulo,
        String area,
        String senioridade,
        String local,
        boolean aceitaIniciante,
        String empresaSlug
) {

    public static VagaResposta de(Vaga v) {
        return new VagaResposta(v.id(), v.titulo(), v.area(), v.senioridade(),
                v.local(), v.aceitaIniciante(), v.empresaSlug());
    }
}
