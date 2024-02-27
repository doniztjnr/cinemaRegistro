/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.controller;

import com.cinemaRegistro.cinemaRegistro.data.CartoonEntity;
import com.cinemaRegistro.cinemaRegistro.service.CartoonService;
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
public class CartoonController {

    @Autowired
    CartoonService cartoonService;
    
    @GetMapping("/listarCartoons")
    public String tableListarCartoon(Model model) {
        List<CartoonEntity> cartoons = cartoonService.getTodosOsCartoons();
        model.addAttribute("cartoons", cartoons);
        model.addAttribute("title", "Cartoons");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "tableListarCartoon.html");
        return "index";
    }

    @GetMapping("/adicionarCartoon")
    public String formAdicionarCartoon(Model model) {
        model.addAttribute("cartoon", new CartoonEntity());
        model.addAttribute("title", "Adicionar cartoon");
        model.addAttribute("navbar", "headerNavBar.html");
        model.addAttribute("content", "formAdicionarCartoon.html");
        return "index";
    }
    
    @PostMapping("/salvarCartoon")
    public String adicionarCartoon(@ModelAttribute CartoonEntity cartoonEntity, Model model) {
        cartoonService.criarCartoon(cartoonEntity);
        return tableListarCartoon(model);
    }
}
