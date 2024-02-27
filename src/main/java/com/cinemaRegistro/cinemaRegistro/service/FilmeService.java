/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.service;

import com.cinemaRegistro.cinemaRegistro.data.FilmeEntity;
import com.cinemaRegistro.cinemaRegistro.data.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Donizete
 */
@Service
public class FilmeService {
    
    @Autowired
    FilmeRepository filmeRepository;
    
    public FilmeEntity criarFilme(FilmeEntity filme) {
        filme.setId(null);
        filmeRepository.save(filme);
        return filme;
    }

    public FilmeEntity getFilmeById(Integer filmeId) {
        return filmeRepository.findById(filmeId).orElse(null);
    }

    public List<FilmeEntity> getTodosOsFilmes() {
        return filmeRepository.findAll();
    }

    public FilmeEntity atualizarFilme(Integer filmeId, FilmeEntity filmeRequest) {
        FilmeEntity filme = getFilmeById(filmeId);
        filme.setNone(filmeRequest.getNone());
        filme.setDataDeLancamento(filmeRequest.getDataDeLancamento());
        filme.setAssistidoNaData(filmeRequest.getAssistidoNaData());
        filmeRepository.save(filme);
        return filme;
    }

    public void deletarFilme(Integer filmeId) {
        FilmeEntity filme = getFilmeById(filmeId);
        filmeRepository.deleteById(filme.getId());
    }
}
