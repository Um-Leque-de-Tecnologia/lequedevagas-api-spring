package br.edu.faculdade.vagas;

/**
 * Frente 3 · quem se candidata.
 *
 * Nenhum campo de senha, de propósito: guardar senha exige cuidado que a
 * disciplina ainda não deu. O campo entra na aula 10, junto com o jeito certo
 * de guardá-la — e aí GET /pessoas deixa de ser público.
 */
public record Pessoa(
    String id,
    String nome,
    String email,
    String area,
    String senioridade
) { }
