package com.helpAnimal.api.model.publicacao;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_AJUDAS")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PublicacaoModel {

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
