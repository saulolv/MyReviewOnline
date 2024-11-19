package org.myreview.Infra.Repositories;

import org.myreview.Domain.Entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
    // Buscar episódios por ID da série
    List<Episode> findBySeriesId(UUID seriesId);
}
