package com.enriquejoseap.cursoJava.service;

import com.enriquejoseap.cursoJava.model.Usuario;
import com.enriquejoseap.cursoJava.repository.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> listUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean validUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        String passwordHashed = usuario.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, password)){
            return true;
        }
        return false;
    }

    @Override
    public Usuario getUsuarioByCredentials(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        String passwordHashed = usuario.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, password)){
            return usuario;
        }
        return null;
    }
}
