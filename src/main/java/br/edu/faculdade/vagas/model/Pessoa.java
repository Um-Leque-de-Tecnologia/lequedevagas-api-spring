package br.edu.faculdade.vagas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Frente 3 · quem se candidata.
 *
 * Nenhum campo de senha, de propósito: ele entra junto com o jeito certo de
 * guardá-lo, na aula de Spring Security.
 */
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Ninguém confere e-mail repetido no service: o unique recusa, e o
    // TratadorDeErros transforma a recusa em 409.
    @Column(nullable = false, unique = true)
    private String email;

    private String area;

    private String senioridade;

    protected Pessoa() { }

    public Pessoa(String nome, String email, String area, String senioridade) {
        trocarDados(nome, email, area, senioridade);
    }

    public void trocarDados(String nome, String email, String area, String senioridade) {
        this.nome = nome;
        this.email = email;
        this.area = area;
        this.senioridade = senioridade;
    }

    public Long id() { return id; }
    public String nome() { return nome; }
    public String email() { return email; }
    public String area() { return area; }
    public String senioridade() { return senioridade; }
}
