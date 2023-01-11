package com.diaraba.projetDeSoutenance.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profilestructure")
public class ProfileStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String activite;

    @NotBlank
    @Size(max = 50)
    private String localisation;

    @NotBlank
    @Size(max = 50)
    private String image;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String slogan;
    @NotBlank
    @Size(max = 50)
    private String numero;
    @ManyToOne
    Structure structure;
}
