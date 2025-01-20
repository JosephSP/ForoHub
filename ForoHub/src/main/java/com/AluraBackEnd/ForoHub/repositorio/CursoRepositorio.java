package com.AluraBackEnd.ForoHub.repositorio;

import com.AluraBackEnd.ForoHub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}