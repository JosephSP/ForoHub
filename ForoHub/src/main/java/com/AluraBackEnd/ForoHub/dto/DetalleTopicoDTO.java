package com.AluraBackEnd.ForoHub.dto;

public record DetalleTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String status,
        String autor,
        String curso
) {
}