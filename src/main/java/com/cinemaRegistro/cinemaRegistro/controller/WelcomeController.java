/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.UsuarioEntity;
import com.cinemaRegistro.cinemaRegistro.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Donizete
 */
@Controller
public class WelcomeController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public String formEntrar(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        model.addAttribute("title", "Entrar");
        model.addAttribute("content", "formEntrar.html");
        return "index";
    }

    @PostMapping("/entrar")
    public String entrarNoSistema(HttpServletRequest request, @Valid @ModelAttribute("usuario") UsuarioEntity usuarioEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/";
        }

        UsuarioEntity usuarioRegistrado = usuarioService.getUsuarioByEmail(usuarioEntity.getEmail());
        if (usuarioRegistrado == null) {
            return "redirect:/";
        } else {
            HttpSession sessao = request.getSession();
            if (sessao != null) {
                sessao.setAttribute("logado", "true");
            }
            return "redirect:/listarFilmes";
        }
    }

    @GetMapping("/registrar")
    public String formRegistrar(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        model.addAttribute("title", "Registrar");
        model.addAttribute("content", "formRegistrar.html");
        return "index";
    }

    @PostMapping("/salvarUsuario")
    public String adicionarUsuario(HttpServletRequest request, @Valid @ModelAttribute("usuario") UsuarioEntity usuarioEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registrar";
        }

        UsuarioEntity usuarioRegistrado = usuarioService.getUsuarioByEmail(usuarioEntity.getEmail());
        if (usuarioRegistrado == null) {
            usuarioService.criarUsuario(usuarioEntity);
            HttpSession sessao = request.getSession();
            if (sessao != null) {
                sessao.setAttribute("logado", "true");
            }
            return "redirect:/listarFilmes";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/sair")
    public String sairdoSistema(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao != null) {
            sessao.removeAttribute("logado");
            return "redirect:/";
        }
        return "redirect:/";
    }
}
