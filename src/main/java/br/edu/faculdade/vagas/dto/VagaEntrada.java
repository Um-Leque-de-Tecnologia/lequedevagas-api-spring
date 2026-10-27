package br.edu.faculdade.vagas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Frente 1 · o que o cliente MANDA.
 *
 * Sem id: quem numera é o banco, e aqui o cliente não tem onde escrever um.
 * As anotações só valem com @Valid no controller. Sem ele, são decoração.
 */
public record VagaEntrada(
        @NotBlank(message = "o título é obrigatório")
        @Size(max = 120, message = "o título passa de 120 caracteres")
        String titulo,

        @NotBlank(message = "a área é obrigatória")
        String area,

        @NotBlank(message = "a senioridade é obrigatória")
        String senioridade,

        @NotBlank(message = "o slug da empresa é obrigatório")
        String empresaSlug,

        String descricao,
        String local,

        // Boolean, e não boolean: com o primitivo, um corpo sem este campo
        // morre no parse do Jackson 3, antes da validação. Com o objeto,
        // "não respondeu" chega como null, e o service decide.
        Boolean aceitaIniciante
) { }
