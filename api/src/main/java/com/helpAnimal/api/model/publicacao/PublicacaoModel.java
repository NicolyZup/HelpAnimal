package com.helpAnimal.api.model.publicacao;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PUBLICACOES")
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "publicacao_id")
    private List<ComentarioModel> comentarios = new ArrayList<>();

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String usuario;

    private Boolean ajudaAtendida=false;
}
