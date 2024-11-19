package org.myreview.Infra.Repositories;

import org.myreview.Domain.Entities.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface ListRepository extends JpaRepository<List, Long> {
    // Buscar listas por usuário
    ArrayList<List> findByUserId(Long userId);
}
