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
public class WelcomeController {
    @GetMapping("/")
    public String formEntrar(Model model) {
        model.addAttribute("title", "Entrar");
        model.addAttribute("content", "formEntrar.html");
        return "index";
    }
    
    @GetMapping("/registrar")
    public String formRegistrar(Model model) {
        model.addAttribute("title", "Registrar");
        model.addAttribute("content", "formRegistrar.html");
        return "index";
    }
}
