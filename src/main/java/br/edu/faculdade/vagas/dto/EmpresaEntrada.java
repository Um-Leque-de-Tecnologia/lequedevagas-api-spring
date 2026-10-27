package br.edu.faculdade.vagas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Frente 2 · o que o cliente MANDA.
 *
 * Ao contrário da vaga, o identificador vem daqui: o slug é escolhido por quem
 * cria, vai na URL e é guardado pela vaga. Por isso é validado de verdade.
 */
public record EmpresaEntrada(
        @NotBlank(message = "o nome é obrigatório")
        String nome,

        @NotBlank(message = "o slug é obrigatório")
        @Pattern(regexp = "[a-z0-9-]+",
                 message = "o slug só aceita minúsculas, números e hífen")
        String slug,

        String site,
        String descricao
) { }
