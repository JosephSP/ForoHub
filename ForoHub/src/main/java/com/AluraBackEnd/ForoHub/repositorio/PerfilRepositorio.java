package com.AluraBackEnd.ForoHub.repositorio;

import com.AluraBackEnd.ForoHub.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepositorio extends JpaRepository<Perfil, Long> {
}