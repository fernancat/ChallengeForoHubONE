package com.fernan.gt.ForoHubChallenge.foroAPI.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String titulo;

    @Lob
    @Column(nullable = false)
    @NotBlank
    private String contenido;

    @Column(name = "fecha_creacion", updatable = false,nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    @NotBlank
    private String autor;

    @Column(nullable = false)
    @NotBlank
    private String curso;

}
