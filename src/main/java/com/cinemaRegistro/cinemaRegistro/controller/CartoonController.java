/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.CartoonEntity;
import com.cinemaRegistro.cinemaRegistro.service.CartoonService;
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
public class CartoonController {

    @Autowired
    CartoonService cartoonService;

    @GetMapping("/listarCartoons")
    public String tableListarCartoon(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            List<CartoonEntity> cartoons = cartoonService.getTodosOsCartoons();
            model.addAttribute("cartoons", cartoons);
            model.addAttribute("title", "Cartoons");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "tableListarCartoon.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/adicionarCartoon")
    public String formAdicionarCartoon(HttpServletRequest request, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            model.addAttribute("cartoon", new CartoonEntity());
            model.addAttribute("title", "Adicionar cartoon");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarCartoon.html");
            return "index";
        }
        return "redirect:/";
    }

    @PostMapping("/salvarCartoon")
    public String adicionarCartoon(HttpServletRequest request, @Valid @ModelAttribute("cartoon") CartoonEntity cartoonEntity, BindingResult result) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            if (result.hasErrors()) {
                return "redirect:/listarCartoons";
            }
            if (cartoonEntity.getId() == null) {
                cartoonService.criarCartoon(cartoonEntity);
            } else {
                cartoonService.atualizarCartoon(cartoonEntity.getId(), cartoonEntity);
            }
            return "redirect:/listarCartoons";
        }
        return "redirect:/";
    }

    @GetMapping("/atualizarCartoon/{id}")
    public String atualizarCartoon(HttpServletRequest request, @PathVariable(value = "id") Integer id, Model model) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            CartoonEntity cartoon = cartoonService.getCartoonById(id);
            model.addAttribute("cartoon", cartoon);
            model.addAttribute("title", "Atualizar cartoon");
            model.addAttribute("navbar", "headerNavBar.html");
            model.addAttribute("content", "formAdicionarCartoon.html");
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/deletarCartoon/{id}")
    public String deletarCartoon(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
        HttpSession sessao = request.getSession();
        if (sessao != null && sessao.getAttribute("logado") == null) {
            return "redirect:/";
        }
        if (sessao != null && sessao.getAttribute("logado").equals("true")) {
            cartoonService.deletarCartoon(id);
            return "redirect:/listarCartoons";
        }
        return "redirect:/";
    }
}
