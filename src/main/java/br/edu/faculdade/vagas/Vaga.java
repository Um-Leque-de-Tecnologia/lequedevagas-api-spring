package br.edu.faculdade.vagas;

/**
 * Frente 1 · o recurso principal da API.
 *
 * Um record é só dado, então NÃO leva anotação: os estereótipos
 * (@Repository, @Service, @RestController) marcam classes que fazem alguma
 * coisa, e um record não faz nada — ele guarda.
 *
 * O id é String, e não número: o que vem da URL é sempre texto, e comparar
 * texto com texto evita conversão em todo lugar. Na aula 05, quando o banco
 * entrar, ele vira Long e a conversão passa a ser trabalho do Spring.
 *
 * O record não muda na aula 03 — mas o uso dele, sim: ele passa a ser usado
 * também como corpo do POST, aceitando que o id chega null e é ignorado.
 * Funciona, e é o suficiente para aquela aula. O incômodo tem nome na aula 04:
 * DTO.
 */
public record Vaga(
    String id,
    String titulo,
    String descricao,
    String area,
    String senioridade,
    String local,
    boolean aceitaIniciante,
    String empresaSlug
) { }
