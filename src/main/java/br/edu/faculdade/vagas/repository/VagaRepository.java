package br.edu.faculdade.vagas.repository;

import br.edu.faculdade.vagas.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Frente 1. Na subida, o Spring Data lê esta declaração e escreve a
 * implementação sozinho: findAll, findById, save, existsById, deleteById, count.
 *
 * As 12 vagas moram em src/main/resources/dados/vagas.sql.
 */
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
