package org.myreview.Infra.Repositories;

import org.myreview.Domain.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
