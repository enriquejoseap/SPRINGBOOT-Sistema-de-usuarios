package com.enriquejoseap.cursoJava.controller;

import com.enriquejoseap.cursoJava.model.Usuario;
import com.enriquejoseap.cursoJava.service.UsuarioService;
import com.enriquejoseap.cursoJava.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8080")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario){
        Usuario usuarioSignIn = usuarioService.getUsuarioByCredentials(usuario.getEmail(), usuario.getPassword());
        if(usuarioSignIn != null){
            String token = jwtUtil.create(String.valueOf(usuarioSignIn.getIdUsuario()), usuarioSignIn.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }
}
