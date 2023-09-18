package com.helpAnimal.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDTO {
    private String usuario;

    private String texto;

    private LocalDate data;
}
