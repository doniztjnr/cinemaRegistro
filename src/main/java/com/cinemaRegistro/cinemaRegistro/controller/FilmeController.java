/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.FilmeEntity;
import com.cinemaRegistro.cinemaRegistro.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Donizete
 */
@Controller
public class FilmeController {
    
    @Autowired
    FilmeService filmeService;
    
    @GetMapping("/listarFilmes")
    public String tableListarFilme(Model model) {
        List<FilmeEntity> filmes = filmeService.getTodosOsFilmes();
        model.addAttribute("filmes", filmes);
        model.addAttribute("title", "Filmes");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarFilme.html");
        return "index";
    }
    
    @GetMapping("/adicionarFilme")
    public String formAdicionarFilme(Model model) {
        model.addAttribute("filme", new FilmeEntity());
        model.addAttribute("title", "Adicionar filme");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarFilme.html");
        return "index";
    }
    
    @PostMapping("/salvarFilme")
    public String adicionarFilme(@ModelAttribute FilmeEntity filmeEntity, Model model) {
        filmeService.criarFilme(filmeEntity);
        return tableListarFilme(model);
    }
}
