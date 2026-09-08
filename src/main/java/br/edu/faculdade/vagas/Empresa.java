package br.edu.faculdade.vagas;

/**
 * Frente 2 · quem publica as vagas.
 *
 * O identificador desta frente é o slug, não o id: é ele que vai na URL, é ele
 * que a Vaga guarda em empresaSlug, e é ele que o cliente escolhe ao criar.
 * O id continua aqui por herança da aula 02 — na aula 05, quando o banco
 * entrar, ele passa a ser gerado por lá.
 */
public record Empresa(
    String id,
    String nome,
    String slug,
    String site,
    String descricao
) { }
