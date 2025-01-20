package com.AluraBackEnd.ForoHub.controlador;

import com.AluraBackEnd.ForoHub.dto.LoginDTO;
import com.AluraBackEnd.ForoHub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Validated LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.correoElectronico(), loginDTO.contrasena());
        Authentication auth = authenticationManager.authenticate(authToken);

        String token = tokenService.generarToken(auth.getName());
        return ResponseEntity.ok("Bearer " + token);
    }
}