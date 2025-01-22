package com.forohub_challenge.forohub.topico.dto;

import com.forohub_challenge.forohub.curso.Curso;
import com.forohub_challenge.forohub.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        Usuario autor,
        Curso curso
) {
}
