package org.myreview.Application.Controllers;

import org.myreview.Application.Services.EpisodeService;
import org.myreview.Domain.Entities.Episode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    // Criar novo episódio
    @PostMapping
    public ResponseEntity<Episode> createEpisode(@RequestBody Episode episode) {
        Episode newEpisode = episodeService.createEpisode(episode);
        return ResponseEntity.ok(newEpisode);
    }

    // Buscar episódio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable UUID id) {
        Optional<Episode> episode = episodeService.getEpisodeById(id);
        return episode.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar episódios por ID da série
    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<Episode>> getEpisodesBySeriesId(@PathVariable UUID seriesId) {
        List<Episode> episodes = episodeService.getEpisodesBySeriesId(seriesId);
        return ResponseEntity.ok(episodes);
    }

    // Atualizar episódio
    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable UUID id, @RequestBody Episode updatedEpisode) {
        try {
            Episode episode = episodeService.updateEpisode(id, updatedEpisode);
            return ResponseEntity.ok(episode);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar episódio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable UUID id) {
        try {
            episodeService.deleteEpisode(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
