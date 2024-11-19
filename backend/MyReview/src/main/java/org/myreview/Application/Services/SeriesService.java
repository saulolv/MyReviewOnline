package org.myreview.Application.Services;

import org.myreview.Domain.Entities.Series;
import org.myreview.Infra.Repositories.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    // Criar uma nova série
    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    // Buscar série por ID
    public Optional<Series> getSeriesById(UUID id) {
        return seriesRepository.findById(id);
    }

    // Listar todas as séries
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    // Atualizar série
    public Series updateSeries(UUID id, Series updatedSeries) {
        return seriesRepository.findById(id).map(existingSeries -> {
            existingSeries.setTitle(updatedSeries.getTitle());
            existingSeries.setGenre(updatedSeries.getGenre());
            existingSeries.setSynopsis(updatedSeries.getSynopsis());
            existingSeries.setStudio(updatedSeries.getStudio());
            existingSeries.setReleaseDate(updatedSeries.getReleaseDate());
            existingSeries.setCoverImage(updatedSeries.getCoverImage());
            existingSeries.setSeasons(updatedSeries.getSeasons());
            existingSeries.setCurrentSeason(updatedSeries.getCurrentSeason());
            existingSeries.setEpisodes(updatedSeries.getEpisodes());
            return seriesRepository.save(existingSeries);
        }).orElseThrow(() -> new IllegalArgumentException("Series not found with ID: " + id));
    }

    // Deletar série
    public void deleteSeries(UUID id) {
        if (seriesRepository.existsById(id)) {
            seriesRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Series not found with ID: " + id);
        }
    }
}
