package com.forohub_challenge.forohub.topico;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existsByTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}