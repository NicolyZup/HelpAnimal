package com.helpAnimal.api.model.publicacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "TB_COMENTARIOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String usuario;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String texto;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private LocalDate data;
}
