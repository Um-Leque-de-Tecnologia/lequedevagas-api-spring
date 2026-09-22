package br.edu.faculdade.vagas;

/**
 * Frente 1 · o contrato de saída — o que a API DEVOLVE (aula 04).
 *
 * Tem id, porque quem inventa o id é a API, e o cliente precisa dele para
 * chamar /vagas/{id} depois. E NÃO tem descricao: a listagem não precisa do
 * texto longo, e saída poder esconder campo é o ponto desta separação — na
 * aula 10, quando Pessoa ganhar senha, é ela que impede a senha de vazar.
 *
 * O par deste record é VagaEntrada. Quem traduz Vaga em VagaResposta é o
 * VagaService, num método só.
 */
public record VagaResposta(
    String id,
    String titulo,
    String area,
    String senioridade,
    String local,
    boolean aceitaIniciante,
    String empresaSlug
) { }
