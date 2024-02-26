/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Donizete
 */
@Controller
public class FilmeController {
    @GetMapping("/listarFilmes")
    public String tableListarFilme(Model model) {
        model.addAttribute("title", "Filmes");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarFilme.html");
        return "index";
    }
    
    @GetMapping("/adicionarFilme")
    public String formAdicionarFilme(Model model) {
        model.addAttribute("title", "Adicionar filme");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarFilme.html");
        return "index";
    }
}
