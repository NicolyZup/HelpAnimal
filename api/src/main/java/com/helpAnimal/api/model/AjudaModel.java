package com.helpAnimal.api.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_AJUDAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjudaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    //impplementar apos sistema de login
    /*private List<ComentarioModel> comentarios = new ArrayList<>();*/

    private Boolean ajudaAtendida=false;
}
