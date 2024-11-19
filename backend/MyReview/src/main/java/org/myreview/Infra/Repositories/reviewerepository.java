package org.myreview.Domain.Repositories;

import org.myreview.Domain.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Buscar reviews por ID do conteúdo
    List<Review> findByContentId(Long contentId);

    // Buscar reviews por ID do usuário
    List<Review> findByUserId(Long userId);
}
