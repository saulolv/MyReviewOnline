package org.myreview.Application.Services;

import org.myreview.Domain.Entities.Content;
import org.myreview.Domain.Repositories.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    // Criar novo conteúdo
    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    // Buscar conteúdo por ID
    public Optional<Content> getContentById(UUID id) {
        return contentRepository.findById(id);
    }

    // Listar todos os conteúdos
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    // Atualizar conteúdo
    public Content updateContent(UUID id, Content updatedContent) {
        return contentRepository.findById(id).map(existingContent -> {
            existingContent.setTitle(updatedContent.getTitle());
            existingContent.setGenre(updatedContent.getGenre());
            existingContent.setSynopsis(updatedContent.getSynopsis());
            existingContent.setStudio(updatedContent.getStudio());
            existingContent.setReleaseDate(updatedContent.getReleaseDate());
            existingContent.setCoverImage(updatedContent.getCoverImage());
            return contentRepository.save(existingContent);
        }).orElseThrow(() -> new IllegalArgumentException("Content not found with ID: " + id));
    }

    // Deletar conteúdo
    public void deleteContent(UUID id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Content not found with ID: " + id);
        }
    }
}
