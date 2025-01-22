package com.forohub_challenge.forohub.controller;

import com.forohub_challenge.forohub.curso.Curso;
import com.forohub_challenge.forohub.curso.CursoRepository;
import com.forohub_challenge.forohub.curso.DatosListadoCurso;
import com.forohub_challenge.forohub.topico.Topico;
import com.forohub_challenge.forohub.topico.dto.DatosListadoTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

   /* @PostMapping
    public ResponseEntity registrarCurso(@RequestBody Curso curso) {
        cursoRepository.save(curso);
        URI uri = URI.create("/cursos/" + curso.getId());
        return ResponseEntity.created(uri).body(curso);
    }


    @GetMapping
    public Page<DatosListadoCurso> listadoCursos(@PageableDefault(size = 5) Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }*/
   @PostMapping
   public ResponseEntity registrarCurso(@RequestBody Curso curso) {
       cursoRepository.save(curso);
       URI uri = URI.create("/cursos/" + curso.getId());
       return ResponseEntity.created(uri).body(curso);
   }
    @GetMapping
    public ResponseEntity <Page<DatosListadoCurso>> listadoCurso(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListadoCurso::new));

    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoCurso> detalleTopico(@PathVariable Long id) {
        Optional<Curso> topico = cursoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DatosListadoCurso(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    }

