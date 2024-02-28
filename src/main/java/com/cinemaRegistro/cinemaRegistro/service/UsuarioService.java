/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.service;

import com.cinemaRegistro.cinemaRegistro.data.UsuarioEntity;
import com.cinemaRegistro.cinemaRegistro.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Donizete
 */
@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {
        usuario.setId(null);
        usuarioRepository.save(usuario);
        return usuario;
    }
    
    public UsuarioEntity getUsuarioByEmail(String usuarioEmail) {
        return usuarioRepository.findByEmail(usuarioEmail);
    }
}
