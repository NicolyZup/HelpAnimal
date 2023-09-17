package com.helpAnimal.api.repository;

import com.helpAnimal.api.model.publicacao.PublicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacaoRepository extends JpaRepository<PublicacaoModel, Long> {
}
