package org.myreview.Infra.Repositories;

import org.myreview.Domain.Entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID> {
}
