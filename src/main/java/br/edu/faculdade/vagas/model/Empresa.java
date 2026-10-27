package br.edu.faculdade.vagas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Frente 2 · quem publica as vagas.
 *
 * O id é gerado pelo banco e não aparece nas URLs: quem identifica a empresa é
 * o slug, que o cliente escolhe ao criar. Por isso o slug é unique na tabela.
 */
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String slug;

    private String site;

    @Column(length = 1000)
    private String descricao;

    protected Empresa() { }

    public Empresa(String nome, String slug, String site, String descricao) {
        this.nome = nome;
        this.slug = slug;
        this.site = site;
        this.descricao = descricao;
    }

    public Long id() { return id; }
    public String nome() { return nome; }
    public String slug() { return slug; }
    public String site() { return site; }
    public String descricao() { return descricao; }
}
