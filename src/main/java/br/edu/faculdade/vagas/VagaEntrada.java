package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Frente 1 · o contrato de entrada — o que o cliente MANDA (aula 04).
 *
 * Sem id: quem inventa o id é a API, e aqui o cliente não tem onde escrever um.
 * Não é uma regra checando nada — o campo simplesmente não existe, e o Jackson
 * descarta o que sobra antes de o nosso código ver.
 *
 * As anotações só valem com @Valid no controller. Sem ele, são decoração.
 */
public record VagaEntrada(
        @NotBlank(message = "o título é obrigatório")
        @Size(max = 120)
        String titulo,

        @NotBlank
        String area,

        @NotBlank
        String senioridade,

        @NotBlank
        String empresaSlug,

        String descricao,
        String local,

        // Boolean, e não boolean: é a pegadinha da aula. Com o primitivo, um
        // corpo sem este campo nem chega na validação — o Jackson 3 do Boot 4
        // recusa mapear null num boolean e o POST morre no parse, com 400 em
        // application/json em vez do problem+json com a lista de campos. Com o
        // objeto, "não respondeu" chega como null, e quem trata isso é o
        // service.
        Boolean aceitaIniciante
) { }
