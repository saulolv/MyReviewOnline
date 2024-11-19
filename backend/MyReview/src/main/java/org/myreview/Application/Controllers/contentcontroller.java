package org.myreview.Application.Controllers;

import org.myreview.Application.Services.ContentService;
import org.myreview.Domain.Entities.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    // Criar novo conteúdo
    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        Content newContent = contentService.createContent(content);
        return ResponseEntity.ok(newContent);
    }

    // Buscar conteúdo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable UUID id) {
        Optional<Content> content = contentService.getContentById(id);
        return content.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos os conteúdos
    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        List<Content> contents = contentService.getAllContents();
        return ResponseEntity.ok(contents);
    }

    // Atualizar conteúdo
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable UUID id, @RequestBody Content updatedContent) {
        try {
            Content content = contentService.updateContent(id, updatedContent);
            return ResponseEntity.ok(content);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar conteúdo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable UUID id) {
        try {
            contentService.deleteContent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
