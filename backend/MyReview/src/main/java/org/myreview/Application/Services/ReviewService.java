package org.myreview.Application.Services;

import org.myreview.Domain.Entities.Review;
import org.myreview.Infra.Repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Criar uma nova review
    public Review createReview(Review review) {
        review.setCreatedAt(new java.util.Date());
        review.setUpdatedAt(new java.util.Date());
        return reviewRepository.save(review);
    }

    // Buscar review por ID
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    // Listar todas as reviews de um conteúdo
    public List<Review> getReviewsByContentId(Long contentId) {
        return reviewRepository.findByContentId(contentId);
    }

    // Listar todas as reviews de um usuário
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    // Atualizar review
    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            existingReview.setUpdatedAt(new java.util.Date());
            return reviewRepository.save(existingReview);
        }).orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + id));
    }

    // Deletar review
    public void deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Review not found with ID: " + id);
        }
    }
}
