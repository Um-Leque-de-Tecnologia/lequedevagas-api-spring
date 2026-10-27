package br.edu.faculdade.vagas.repository;

import br.edu.faculdade.vagas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Frente 2. O CRUD pronto busca pelo id, e esta frente busca pelo slug.
 * Declarar findBySlug basta: o Spring Data lê o nome do método e escreve o
 * where slug = ?. Até onde dá para ir só com o nome é a aula 08.
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findBySlug(String slug);
}
