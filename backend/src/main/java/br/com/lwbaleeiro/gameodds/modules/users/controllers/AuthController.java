package br.com.lwbaleeiro.gameodds.modules.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.lwbaleeiro.gameodds.modules.users.UserEntity;
import br.com.lwbaleeiro.gameodds.modules.users.dto.AuthRequest;
import br.com.lwbaleeiro.gameodds.modules.users.dto.AuthResponse;
import br.com.lwbaleeiro.gameodds.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> create(@RequestBody @Valid AuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
