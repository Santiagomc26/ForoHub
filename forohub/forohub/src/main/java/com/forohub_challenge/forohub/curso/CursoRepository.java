package com.forohub_challenge.forohub.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Curso c WHERE c.nombre = :nombre")
    boolean existsByNombre(@Param("nombre") String nombre);
}
