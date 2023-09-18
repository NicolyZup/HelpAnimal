package com.helpAnimal.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDTO {
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
