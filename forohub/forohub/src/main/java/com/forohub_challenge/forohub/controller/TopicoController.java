package com.forohub_challenge.forohub.controller;

import com.forohub_challenge.forohub.curso.CursoRepository;
import com.forohub_challenge.forohub.topico.Topico;
import com.forohub_challenge.forohub.topico.TopicoRepository;
import com.forohub_challenge.forohub.topico.dto.DatosActualizarTopico;
import com.forohub_challenge.forohub.topico.dto.DatosListadoTopico;
import com.forohub_challenge.forohub.topico.dto.DatosRegistroTopico;
import com.forohub_challenge.forohub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        // Validar si los IDs de autor y curso existen
        boolean autorExiste = usuarioRepository.existsById(datosRegistroTopico.autor().getId());
        boolean cursoExiste = cursoRepository.existsById(datosRegistroTopico.curso().getId());

        if (!autorExiste) {
            return ResponseEntity.badRequest().body("El autor con ID " + datosRegistroTopico.autor().getId() + " no existe.");
        }

        if (!cursoExiste) {
            return ResponseEntity.badRequest().body("El curso con ID " + datosRegistroTopico.curso().getId() + " no existe.");
        }

        // Validar si ya existe un tópico con el mismo título y mensaje
        boolean topicoDuplicado = topicoRepository.existsByTituloAndMensaje(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje()
        );

        if (topicoDuplicado) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Crear y guardar el nuevo tópico
        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);
        URI uri = URI.create("/topicos/" + topico.getId());
        return ResponseEntity.created(uri).body(new DatosListadoTopico(topico));
    }



    @GetMapping
    public ResponseEntity <Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> detalleTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DatosListadoTopico(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        // Verificar si el tópico existe
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();

        // Validar si los datos actualizados generan duplicados
        boolean duplicado = topicoRepository.existsByTituloAndMensaje(
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje()
        );

        if (duplicado) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Validar la presencia de autor y curso
        if (datosActualizarTopico.autor() == null || datosActualizarTopico.autor().getId() == null) {
            return ResponseEntity.badRequest().body("El autor no ha sido proporcionado.");
        }

        if (datosActualizarTopico.curso() == null || datosActualizarTopico.curso().getId() == null) {
            return ResponseEntity.badRequest().body("El curso no ha sido proporcionado.");
        }

        // Verificar si los IDs de autor y curso existen
        boolean autorExiste = usuarioRepository.existsById(datosActualizarTopico.autor().getId());
        if (!autorExiste) {
            return ResponseEntity.badRequest().body("El autor con ID " + datosActualizarTopico.autor().getId() + " no existe.");
        }

        boolean cursoExiste = cursoRepository.existsById(datosActualizarTopico.curso().getId());
        if (!cursoExiste) {
            return ResponseEntity.badRequest().body("El curso con ID " + datosActualizarTopico.curso().getId() + " no existe.");
        }

        // Actualizar los datos del tópico
        topico.actualizarDatos(datosActualizarTopico);
        topicoRepository.save(topico);

        // Retornar respuesta exitosa con los datos actualizados
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no existe, retorna 404
        }

        // Eliminar el tópico si existe
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Respuesta 204 No Content
    }

}