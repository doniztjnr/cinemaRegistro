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
public class SerieController {
    @GetMapping("/listarSeries")
    public String tableListarSerie(Model model) {
        model.addAttribute("title", "Series");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarSerie.html");
        return "index";
    }
    
    @GetMapping("/adicionarSerie")
    public String formAdicionarSerie(Model model) {
        model.addAttribute("title", "Adicionar serie");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarSerie.html");
        return "index";
    }
}
