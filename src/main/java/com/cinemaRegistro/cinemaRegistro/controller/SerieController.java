/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.SerieEntity;
import com.cinemaRegistro.cinemaRegistro.service.SerieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String tableListarSerie(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            List<SerieEntity> series = serieService.getTodasAsSeries();
            model.addAttribute("series", series);
            model.addAttribute("title", "Series");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "tableListarSerie.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/adicionarSerie")
    public String formAdicionarSerie(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            model.addAttribute("serie", new SerieEntity());
            model.addAttribute("title", "Adicionar serie");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarSerie.html");
            return "index";
        }
        return "redirect:/";
    }

    @PostMapping("/salvarSerie")
    public String adicionarSerie(HttpServletRequest request, @Valid @ModelAttribute("serie") SerieEntity serieEntity, BindingResult result, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            if (result.hasErrors()) {
                return "redirect:/listarSeries";
            }
            if (serieEntity.getId() == null) {
                serieService.criarSerie(serieEntity);
            } else {
                serieService.atualizarSerie(serieEntity.getId(), serieEntity);
            }
            return "redirect:/listarSeries";
        }
        return "redirect:/";
    }

    @GetMapping("/atualizarSerie/{id}")
    public String atualizarSerie(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            SerieEntity serie = serieService.getSerieById(id);
            model.addAttribute("serie", serie);
            model.addAttribute("title", "Atualizar serie");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarSerie.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/deletarSerie/{id}")
    public String deletarSerie(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            serieService.deletarSerie(id);
            return "redirect:/listarSeries";
        }
        return "redirect:/";
    }
}
