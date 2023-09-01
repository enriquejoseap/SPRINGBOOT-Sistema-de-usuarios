package com.enriquejoseap.cursoJava.controller;

import com.enriquejoseap.cursoJava.model.Usuario;
import com.enriquejoseap.cursoJava.service.UsuarioService;
import com.enriquejoseap.cursoJava.util.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8080")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token){
        String idUsuario = jwtUtil.getKey(token);
        return idUsuario != null;
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/api/users")
    public List<Usuario> listUsuarios(@RequestHeader("Authorization") String token){
        if(validarToken(token)){
            return usuarioService.listUsuarios();
        }
        return new ArrayList<>();
    }
    @PostMapping("/api/users")
    public void createUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioService.saveUsuario(usuario);
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUsuario(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        Map<String, Boolean> res = new HashMap<>();
        if(!validarToken(token)){
            res.put("Unauthorized", Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        Usuario usuario = usuarioService.getUsuarioById(id);
        usuarioService.deleteUsuario(usuario);
        res.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(res);
    }
}
