package com.helpAnimal.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PublicacaoDTO {

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String animal;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String descricao;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String localidade;

    private Boolean ajudaAtendida = false;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String usuario;

    private List<ComentarioResponseDTO> comentarios = new ArrayList<>();
}
