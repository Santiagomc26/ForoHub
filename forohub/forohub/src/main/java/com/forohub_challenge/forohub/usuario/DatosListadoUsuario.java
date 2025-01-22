package com.forohub_challenge.forohub.usuario;

public record DatosListadoUsuario(String nombre, String email) {
    // Constructor que inicializa a partir de un objeto Usuario
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail());
    }
}
