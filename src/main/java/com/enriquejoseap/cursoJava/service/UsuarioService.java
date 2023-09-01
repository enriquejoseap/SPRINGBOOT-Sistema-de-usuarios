package com.enriquejoseap.cursoJava.service;

import com.enriquejoseap.cursoJava.model.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> listUsuarios();
    public Usuario getUsuarioById(Integer idUsuario);
    public void deleteUsuario(Usuario usuario);
    public Usuario saveUsuario(Usuario usuario);
    public boolean validUsuario(String email, String password);
    public Usuario getUsuarioByCredentials(String email, String password);
}
