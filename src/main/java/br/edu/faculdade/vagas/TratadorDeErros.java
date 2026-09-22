package br.edu.faculdade.vagas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail campoInvalido(MethodArgumentNotValidException e) {

        ProblemDetail problema = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                        "Um ou mais campos estão inválidos.");
        problema.setTitle("Dados inválidos");

        Map<String, String> erros = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(f -> erros.put(f.getField(), f.getDefaultMessage()));
        problema.setProperty("campos", erros);

        return problema;
    }
}
