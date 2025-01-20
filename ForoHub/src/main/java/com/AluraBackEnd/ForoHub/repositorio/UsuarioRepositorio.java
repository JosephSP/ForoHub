package com.AluraBackEnd.ForoHub.repositorio;

import com.AluraBackEnd.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByCorreoElectronico(String correoElectronico);
}