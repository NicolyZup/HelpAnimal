package com.helpAnimal.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_AJUDAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AjudaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String animal;
    private String descricao;
    private String localidade;
    private Boolean ajudaAtendida;
}
