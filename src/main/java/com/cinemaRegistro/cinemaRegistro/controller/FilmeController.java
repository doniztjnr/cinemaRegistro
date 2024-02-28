/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.FilmeEntity;
import com.cinemaRegistro.cinemaRegistro.service.FilmeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String adicionarFilme(@Valid @ModelAttribute("filme") FilmeEntity filmeEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return tableListarFilme(model);
        }
        if (filmeEntity.getId() == null) {
            filmeService.criarFilme(filmeEntity);
        } else {
            filmeService.atualizarFilme(filmeEntity.getId(), filmeEntity);
        }
        return tableListarFilme(model);
    }

    @GetMapping("/atualizarFilme/{id}")
    public String atualizarFilme(@PathVariable(value = "id") Integer id, Model model) {
        FilmeEntity filme = filmeService.getFilmeById(id);
        model.addAttribute("filme", filme);
        model.addAttribute("title", "Atualizar filme");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarFilme.html");
        return "index";
    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable(value = "id") Integer id, Model model) {
        filmeService.deletarFilme(id);
        return tableListarFilme(model);
    }
}
