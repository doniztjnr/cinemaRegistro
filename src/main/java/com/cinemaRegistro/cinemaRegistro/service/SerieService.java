/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinemaRegistro.cinemaRegistro.service;

import com.cinemaRegistro.cinemaRegistro.data.SerieEntity;
import com.cinemaRegistro.cinemaRegistro.data.SerieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Donizete
 */
@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;

    public SerieEntity criarSerie(SerieEntity serie) {
        serie.setId(null);
        serieRepository.save(serie);
        return serie;
    }

    public SerieEntity getSerieById(Integer serieId) {
        return serieRepository.findById(serieId).orElse(null);
    }

    public List<SerieEntity> getTodasAsSeries() {
        return serieRepository.findAll();
    }

    public SerieEntity atualizarSerie(Integer serieId, SerieEntity serieRequest) {
        SerieEntity serie = getSerieById(serieId);
        serie.setNone(serieRequest.getNone());
        serie.setDataDeLancamento(serieRequest.getDataDeLancamento());
        serie.setAssistidoNaData(serieRequest.getAssistidoNaData());
        serie.setTemporada(serieRequest.getTemporada());
        serie.setTotalDeEpisodio(serieRequest.getTotalDeEpisodio());
        serieRepository.save(serie);
        return serie;
    }

    public void deletarSerie(Integer serieId) {
        SerieEntity serie = getSerieById(serieId);
        serieRepository.deleteById(serie.getId());
    }
}
