package br.edu.faculdade.vagas.repository;

import br.edu.faculdade.vagas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/** Frente 3. O CRUD inteiro vem do JpaRepository. */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
