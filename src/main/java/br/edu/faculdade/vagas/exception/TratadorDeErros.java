package br.edu.faculdade.vagas.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Um lugar só para o erro, para a API inteira, sempre em Problem Details.
 *
 * O Spring escolhe o método pelo TIPO da exceção — por isso nenhum controller
 * tem try/catch. Acrescentar um caso é acrescentar um método aqui.
 */
@RestControllerAdvice
public class TratadorDeErros {

    // Corpo inválido: @Valid num @RequestBody.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail corpoInvalido(MethodArgumentNotValidException e) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Um ou mais campos estão inválidos.");
        p.setTitle("Dados inválidos");
        // LinkedHashMap: os campos saem na ordem em que foram declarados.
        Map<String, String> campos = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(f -> campos.put(f.getField(), f.getDefaultMessage()));
        p.setProperty("campos", campos);
        return p;
    }

    // Parâmetro de URL inválido: @Validated na classe + @Size num @RequestParam.
    // É outra exceção, com os erros guardados em outra estrutura.
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail parametroInvalido(ConstraintViolationException e) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Um parâmetro da busca está inválido.");
        p.setTitle("Parâmetro inválido");
        Map<String, String> campos = new LinkedHashMap<>();
        e.getConstraintViolations().forEach(v ->
                campos.put(v.getPropertyPath().toString(), v.getMessage()));
        p.setProperty("campos", campos);
        return p;
    }

    // O id da URL é Long, e "abc" não vira número: o Spring recusa antes de
    // chamar o controller.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail tipoErrado(MethodArgumentTypeMismatchException e) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "O valor '" + e.getValue() + "' não serve para o parâmetro '" + e.getName() + "'.");
        p.setTitle("Parâmetro inválido");
        return p;
    }

    // Conflito que o service detectou antes de gravar (o slug da frente 2).
    @ExceptionHandler(RecursoJaExiste.class)
    public ProblemDetail jaExiste(RecursoJaExiste e) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        p.setTitle("Conflito");
        return p;
    }

    // Conflito que o BANCO detectou (unique, not null, chave estrangeira). A
    // mensagem original traz o SQL e o nome da constraint: ela fica no log, e
    // o cliente recebe uma frase que não ensina nada sobre as nossas tabelas.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail conflitoNoBanco(DataIntegrityViolationException e) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, "O dado conflita com um que já está gravado.");
        p.setTitle("Conflito");
        return p;
    }
}
