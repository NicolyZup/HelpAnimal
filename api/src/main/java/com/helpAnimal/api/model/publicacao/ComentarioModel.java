package com.helpAnimal.api.model.publicacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String usuario;

    private String texto;

    private LocalDate data;
}
