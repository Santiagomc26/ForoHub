package com.forohub_challenge.forohub.topico.dto;

import com.forohub_challenge.forohub.topico.Topico;
import com.forohub_challenge.forohub.usuario.Usuario;
import com.forohub_challenge.forohub.curso.Curso;

public record DatosListadoTopico(
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}