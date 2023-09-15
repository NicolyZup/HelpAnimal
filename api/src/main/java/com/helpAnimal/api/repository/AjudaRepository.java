package com.helpAnimal.api.repository;

import com.helpAnimal.api.model.AjudaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjudaRepository extends JpaRepository<AjudaModel, Long> {
}
