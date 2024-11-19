package org.myreview.Infra.Repositories;

import org.myreview.Domain.Entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
