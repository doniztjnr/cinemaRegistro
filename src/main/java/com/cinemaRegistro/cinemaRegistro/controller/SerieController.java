/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.SerieEntity;
import com.cinemaRegistro.cinemaRegistro.service.SerieService;
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
public class SerieController {

    @Autowired
    SerieService serieService;

    @GetMapping("/listarSeries")
    public String tableListarSerie(Model model) {
        List<SerieEntity> series = serieService.getTodasAsSeries();
        model.addAttribute("series", series);
        model.addAttribute("title", "Series");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarSerie.html");
        return "index";
    }

    @GetMapping("/adicionarSerie")
    public String formAdicionarSerie(Model model) {
        model.addAttribute("serie", new SerieEntity());
        model.addAttribute("title", "Adicionar serie");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarSerie.html");
        return "index";
    }

    @PostMapping("/salvarSerie")
    public String adicionarSerie(@Valid @ModelAttribute("serie") SerieEntity serieEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return tableListarSerie(model);
        }
        if (serieEntity.getId() == null) {
            serieService.criarSerie(serieEntity);
        } else {
            serieService.atualizarSerie(serieEntity.getId(), serieEntity);
        }
        return tableListarSerie(model);
    }

    @GetMapping("/atualizarSerie/{id}")
    public String atualizarSerie(@PathVariable(value = "id") Integer id, Model model) {
        SerieEntity serie = serieService.getSerieById(id);
        model.addAttribute("serie", serie);
        model.addAttribute("title", "Atualizar serie");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarSerie.html");
        return "index";
    }

    @GetMapping("/deletarSerie/{id}")
    public String deletarSerie(@PathVariable(value = "id") Integer id, Model model) {
        serieService.deletarSerie(id);
        return tableListarSerie(model);
    }
}
