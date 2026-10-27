package br.edu.faculdade.vagas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Frente 1 · a vaga, uma linha da tabela VAGA.
 *
 * Não é record: a JPA cria o objeto vazio e preenche os campos depois, e um
 * record não tem construtor sem argumentos nem campo que dê para preencher.
 *
 * Os métodos de leitura têm o nome do record antigo (titulo(), e não
 * getTitulo()). A JPA lê direto dos campos, porque o @Id está num campo, e o
 * Jackson nunca vê esta classe: quem sai pela API é o VagaResposta.
 */
@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String senioridade;

    private String local;

    private boolean aceitaIniciante;

    // Um texto solto, por enquanto. Na aula 07 vira @ManyToOne Empresa.
    @Column(nullable = false)
    private String empresaSlug;

    // Exigido pela JPA, e só por ela. protected: o nosso código não usa.
    protected Vaga() { }

    public Vaga(String titulo, String descricao, String area, String senioridade,
                String local, boolean aceitaIniciante, String empresaSlug) {
        trocarDados(titulo, descricao, area, senioridade, local, aceitaIniciante, empresaSlug);
    }

    // O PUT troca tudo menos o id. Um método com nome, em vez de sete setters.
    public void trocarDados(String titulo, String descricao, String area, String senioridade,
                            String local, boolean aceitaIniciante, String empresaSlug) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.area = area;
        this.senioridade = senioridade;
        this.local = local;
        this.aceitaIniciante = aceitaIniciante;
        this.empresaSlug = empresaSlug;
    }

    public Long id() { return id; }
    public String titulo() { return titulo; }
    public String descricao() { return descricao; }
    public String area() { return area; }
    public String senioridade() { return senioridade; }
    public String local() { return local; }
    public boolean aceitaIniciante() { return aceitaIniciante; }
    public String empresaSlug() { return empresaSlug; }
}
