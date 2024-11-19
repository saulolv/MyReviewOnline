package org.myreview.Application.Services;

import org.myreview.Domain.Entities.List;
import org.myreview.Domain.Repositories.ListRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListService {

    private final ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    // Criar nova lista
    public List createList(List list) {
        return listRepository.save(list);
    }

    // Buscar lista por ID
    public Optional<List> getListById(Long id) {
        return listRepository.findById(id);
    }

    // Listar todas as listas de um usuário
    public java.util.List<List> getListsByUserId(Long userId) {
        return listRepository.findByUserId(userId);
    }

    // Atualizar lista
    public List updateList(Long id, List updatedList) {
        return listRepository.findById(id).map(existingList -> {
            existingList.setName(updatedList.getName());
            existingList.setDescription(updatedList.getDescription());
            existingList.setIsPublic(updatedList.getIsPublic());
            return listRepository.save(existingList);
        }).orElseThrow(() -> new IllegalArgumentException("List not found with ID: " + id));
    }

    // Deletar lista
    public void deleteList(Long id) {
        if (listRepository.existsById(id)) {
            listRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("List not found with ID: " + id);
        }
    }
}
