package com.forohub_challenge.forohub.curso;

public record DatosListadoCurso(String nombre, String categoria) {
    // Constructor que inicializa a partir de un objeto Curso
    public DatosListadoCurso(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
