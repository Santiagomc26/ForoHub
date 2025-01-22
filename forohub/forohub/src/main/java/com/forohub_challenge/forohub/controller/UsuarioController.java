package com.forohub_challenge.forohub.controller;

import com.forohub_challenge.forohub.usuario.DatosListadoUsuario;
import com.forohub_challenge.forohub.usuario.Usuario;
import com.forohub_challenge.forohub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        URI uri = URI.create("/usuarios/" + usuario.getId());
        return ResponseEntity.created(uri).body(usuario);
    }
    @GetMapping
    public ResponseEntity <Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> detalleTopico(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(value -> ResponseEntity.ok(new DatosListadoUsuario(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
