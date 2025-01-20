package com.AluraBackEnd.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long cursoId
) {
}