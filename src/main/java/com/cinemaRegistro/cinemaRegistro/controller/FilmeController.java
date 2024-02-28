/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.FilmeEntity;
import com.cinemaRegistro.cinemaRegistro.service.FilmeService;
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
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @GetMapping("/listarFilmes")
    public String tableListarFilme(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            List<FilmeEntity> filmes = filmeService.getTodosOsFilmes();
            model.addAttribute("filmes", filmes);
            model.addAttribute("title", "Filmes");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "tableListarFilme.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/adicionarFilme")
    public String formAdicionarFilme(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            model.addAttribute("filme", new FilmeEntity());
            model.addAttribute("title", "Adicionar filme");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarFilme.html");
            return "index";
        }
        return "redirect:/";
    }

    @PostMapping("/salvarFilme")
    public String adicionarFilme(HttpServletRequest request, @Valid @ModelAttribute("filme") FilmeEntity filmeEntity, BindingResult result) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            if (result.hasErrors()) {
                return "redirect:/listarFilmes";
            }
            if (filmeEntity.getId() == null) {
                filmeService.criarFilme(filmeEntity);
            } else {
                filmeService.atualizarFilme(filmeEntity.getId(), filmeEntity);
            }
            return "redirect:/listarFilmes";
        }
        return "redirect:/";
    }

    @GetMapping("/atualizarFilme/{id}")
    public String atualizarFilme(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            FilmeEntity filme = filmeService.getFilmeById(id);
            model.addAttribute("filme", filme);
            model.addAttribute("title", "Atualizar filme");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarFilme.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            filmeService.deletarFilme(id);
            return "redirect:/listarFilmes"; 
        }
        return "redirect:/";
    }
}
