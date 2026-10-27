package br.edu.faculdade.vagas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/** Frente 3 · o que o cliente MANDA. */
public record PessoaEntrada(
        @NotBlank(message = "o nome é obrigatório")
        String nome,

        // As DUAS: @Email sozinha aceita null e texto vazio — ela confere o
        // formato, não a presença. Quem cobra presença é o @NotBlank.
        @NotBlank(message = "o e-mail é obrigatório")
        @Email(message = "e-mail com formato inválido")
        String email,

        String area,
        String senioridade
) { }
