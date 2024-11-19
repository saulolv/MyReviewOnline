package org.myreview.Application.Services;

import org.myreview.Domain.Entities.Episode;
import org.myreview.Infra.Repositories.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    // Criar um novo episódio
    public Episode createEpisode(Episode episode) {
        return episodeRepository.save(episode);
    }

    // Buscar episódio por ID
    public Optional<Episode> getEpisodeById(UUID id) {
        return episodeRepository.findById(id);
    }

    // Listar todos os episódios de uma série
    public List<Episode> getEpisodesBySeriesId(UUID seriesId) {
        return episodeRepository.findBySeriesId(seriesId);
    }

    // Atualizar episódio
    public Episode updateEpisode(UUID id, Episode updatedEpisode) {
        return episodeRepository.findById(id).map(existingEpisode -> {
            existingEpisode.setTitle(updatedEpisode.getTitle());
            existingEpisode.setSynopsis(updatedEpisode.getSynopsis());
            existingEpisode.setDuration(updatedEpisode.getDuration());
            existingEpisode.setSeason(updatedEpisode.getSeason());
            existingEpisode.setEpisode(updatedEpisode.getEpisode());
            existingEpisode.setReleaseDate(updatedEpisode.getReleaseDate());
            return episodeRepository.save(existingEpisode);
        }).orElseThrow(() -> new IllegalArgumentException("Episode not found with ID: " + id));
    }

    // Deletar episódio
    public void deleteEpisode(UUID id) {
        if (episodeRepository.existsById(id)) {
            episodeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Episode not found with ID: " + id);
        }
    }
}
