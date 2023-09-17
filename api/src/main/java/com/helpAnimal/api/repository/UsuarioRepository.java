package com.helpAnimal.api.repository;

import com.helpAnimal.api.model.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
    UserDetails findByLogin(String login);
}
