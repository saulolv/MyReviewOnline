package org.myreview.Application.Controllers;

import org.myreview.Application.Services.ListService;
import org.myreview.Domain.Entities.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lists")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    // Criar nova lista
    @PostMapping
    public ResponseEntity<List> createList(@RequestBody List list) {
        List newList = listService.createList(list);
        return ResponseEntity.ok(newList);
    }

    // Buscar lista por ID
    @GetMapping("/{id}")
    public ResponseEntity<List> getListById(@PathVariable Long id) {
        Optional<List> list = listService.getListById(id);
        return list.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todas as listas de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<java.util.List<List>> getListsByUserId(@PathVariable Long userId) {
        java.util.List<List> lists = listService.getListsByUserId(userId);
        return ResponseEntity.ok(lists);
    }

    // Atualizar lista
    @PutMapping("/{id}")
    public ResponseEntity<List> updateList(@PathVariable Long id, @RequestBody List updatedList) {
        try {
            List list = listService.updateList(id, updatedList);
            return ResponseEntity.ok(list);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar lista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        try {
            listService.deleteList(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
