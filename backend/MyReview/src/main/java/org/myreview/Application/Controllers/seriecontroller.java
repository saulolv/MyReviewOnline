package org.myreview.Application.Controllers;

import org.myreview.Application.Services.SeriesService;
import org.myreview.Domain.Entities.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    // Criar nova série
    @PostMapping
    public ResponseEntity<Series> createSeries(@RequestBody Series series) {
        Series newSeries = seriesService.createSeries(series);
        return ResponseEntity.ok(newSeries);
    }

    // Buscar série por ID
    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeriesById(@PathVariable UUID id) {
        Optional<Series> series = seriesService.getSeriesById(id);
        return series.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todas as séries
    @GetMapping
    public ResponseEntity<List<Series>> getAllSeries() {
        List<Series> seriesList = seriesService.getAllSeries();
        return ResponseEntity.ok(seriesList);
    }

    // Atualizar série
    @PutMapping("/{id}")
    public ResponseEntity<Series> updateSeries(@PathVariable UUID id, @RequestBody Series updatedSeries) {
        try {
            Series series = seriesService.updateSeries(id, updatedSeries);
            return ResponseEntity.ok(series);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar série
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable UUID id) {
        try {
            seriesService.deleteSeries(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
