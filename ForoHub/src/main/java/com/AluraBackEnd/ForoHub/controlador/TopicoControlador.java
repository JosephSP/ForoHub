package com.AluraBackEnd.ForoHub.controlador;

import com.AluraBackEnd.ForoHub.dto.TopicoDTO;
import com.AluraBackEnd.ForoHub.model.Topico;
import com.AluraBackEnd.ForoHub.repositorio.CursoRepositorio;
import com.AluraBackEnd.ForoHub.repositorio.TopicoRepositorio;
import com.AluraBackEnd.ForoHub.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Validated TopicoDTO topicoDTO) {
        var autor = usuarioRepositorio.findById(topicoDTO.autorId()).orElseThrow(
                () -> new IllegalArgumentException("Autor no encontrado"));
        var curso = cursoRepositorio.findById(topicoDTO.cursoId()).orElseThrow(
                () -> new IllegalArgumentException("Curso no encontrado"));

        var existe = topicoRepositorio.findByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje());
        if (existe.isPresent()) {
            return ResponseEntity.badRequest().body("Tópico duplicado");
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.titulo());
        topico.setMensaje(topicoDTO.mensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus("Abierto");
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepositorio.save(topico);
        return ResponseEntity.ok("Tópico registrado exitosamente");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Validated ActualizarTopicoDTO actualizarDTO) {

        var topicoOptional = topicoRepositorio.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var curso = cursoRepositorio.findById(actualizarDTO.cursoId()).orElseThrow(
                () -> new IllegalArgumentException("Curso no encontrado"));

        var topico = topicoOptional.get();
        topico.setTitulo(actualizarDTO.titulo());
        topico.setMensaje(actualizarDTO.mensaje());
        topico.setCurso(curso);

        topicoRepositorio.save(topico);
        return ResponseEntity.ok("Tópico actualizado exitosamente");
    }
    
    @GetMapping
    public ResponseEntity<?> listarTopicos() {
        var topicos = topicoRepositorio.findAll();

        var respuesta = topicos.stream().map(topico -> new DetalleTopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        )).toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleTopico(@PathVariable Long id) {
        var topicoOptional = topicoRepositorio.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();
        var detalle = new DetalleTopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(detalle);
    }
}