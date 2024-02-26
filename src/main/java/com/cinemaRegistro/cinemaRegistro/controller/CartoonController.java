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
public class CartoonController {
    @GetMapping("/listarCartoons")
    public String tableListarCartoon(Model model) {
        model.addAttribute("title", "Cartoons");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarCartoon.html");
        return "index";
    }
    
    @GetMapping("/adicionarCartoon")
    public String formAdicionarCartoon(Model model) {
        model.addAttribute("title", "Adicionar cartoon");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarCartoon.html");
        return "index";
    }
}
