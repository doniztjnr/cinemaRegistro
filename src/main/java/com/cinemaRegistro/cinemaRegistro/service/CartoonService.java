/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.service;

import com.cinemaRegistro.cinemaRegistro.data.CartoonEntity;
import com.cinemaRegistro.cinemaRegistro.data.CartoonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Donizete
 */
@Service
public class CartoonService {

    @Autowired
    CartoonRepository cartoonRepository;

    public CartoonEntity criarCartoon(CartoonEntity cartoon) {
        cartoon.setId(null);
        cartoonRepository.save(cartoon);
        return cartoon;
    }

    public CartoonEntity getCartoonById(Integer cartoonId) {
        return cartoonRepository.findById(cartoonId).orElse(null);
    }

    public List<CartoonEntity> getTodosOsCartoons() {
        return cartoonRepository.findAll();
    }

    public CartoonEntity atualizarCartoon(Integer cartoonId, CartoonEntity cartoonRequest) {
        CartoonEntity cartoon = getCartoonById(cartoonId);
        cartoon.setNome(cartoonRequest.getNome());
        cartoon.setDataDeLancamento(cartoonRequest.getDataDeLancamento());
        cartoon.setAssistidoNaData(cartoonRequest.getAssistidoNaData());
        cartoon.setTemporada(cartoonRequest.getTemporada());
        cartoon.setTotalDeEpisodio(cartoonRequest.getTotalDeEpisodio());
        cartoonRepository.save(cartoon);
        return cartoon;
    }

    public void deletarCartoon(Integer cartoonId) {
        CartoonEntity cartoon = getCartoonById(cartoonId);
        cartoonRepository.deleteById(cartoon.getId());
    }
}
