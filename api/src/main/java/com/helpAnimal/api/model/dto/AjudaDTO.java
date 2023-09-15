package com.helpAnimal.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjudaDTO {

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
}
