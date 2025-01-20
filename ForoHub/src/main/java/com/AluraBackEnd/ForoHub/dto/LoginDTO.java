package com.AluraBackEnd.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String correoElectronico,
        @NotBlank String contrasena
) {
}