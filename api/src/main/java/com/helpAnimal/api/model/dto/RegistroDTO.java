package com.helpAnimal.api.model.dto;

import com.helpAnimal.api.model.usuario.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegistroDTO {
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String login;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private UsuarioRole role;
}
