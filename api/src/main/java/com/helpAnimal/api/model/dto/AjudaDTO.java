package com.helpAnimal.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AjudaDTO {

    @Column(nullable = false)
    private String animal;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String localidade;

    private Boolean ajudaAtendida = false;
}