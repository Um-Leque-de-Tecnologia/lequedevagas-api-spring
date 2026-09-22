package br.edu.faculdade.vagas;

/**
 * Frente 1 · o modelo do recurso principal da API.
 *
 * Um record é só dado, então NÃO leva anotação: os estereótipos
 * (@Repository, @Service, @RestController) marcam classes que fazem alguma
 * coisa, e um record não faz nada — ele guarda.
 *
 * O id é String, e não número: o que vem da URL é sempre texto, e comparar
 * texto com texto evita conversão em todo lugar. Na aula 05, quando o banco
 * entrar, ele vira Long e a conversão passa a ser trabalho do Spring.
 *
 * Até a aula 03 este record era também o corpo do POST. A aula 04 separou as
 * duas coisas: o que entra é VagaEntrada, o que sai é VagaResposta, e a Vaga
 * fica do lado de dentro — do service para o repository. É por isso que
 * descricao pode existir aqui sem aparecer na resposta.
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
