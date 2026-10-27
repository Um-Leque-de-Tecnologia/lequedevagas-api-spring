package br.edu.faculdade.vagas.exception;

/**
 * Regra de negócio, e não de formato: "esse slug já está em uso?" depende do
 * que já existe, e anotação nenhuma sabe disso. O TratadorDeErros responde 409.
 *
 * RuntimeException para o controller não precisar saber que o service pode
 * falhar assim: o Spring leva a exceção até o tratador sozinho.
 */
public class RecursoJaExiste extends RuntimeException {

    public RecursoJaExiste(String oque, String identificador) {
        super(oque + " " + identificador + " já existe.");
    }
}
