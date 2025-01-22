package com.forohub_challenge.forohub.topico;

import com.forohub_challenge.forohub.topico.dto.DatosActualizarTopico;
import com.forohub_challenge.forohub.topico.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.forohub_challenge.forohub.curso.Curso;
import com.forohub_challenge.forohub.usuario.Usuario;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private boolean activo;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;
    // Constructor vacío (obligatorio para Hibernate)
    public Topico() {
    }

    // Constructor con todos los campos (menos el ID, que es autogenerado)

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }


}