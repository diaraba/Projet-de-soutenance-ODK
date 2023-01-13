package com.diaraba.projetDeSoutenance.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "profilestructure")
public class ProfileStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String activite;

    @NotBlank
    private String localisation;


    private String image;

    @NotBlank
    @Size(max =10000)
    private String description;

    @NotBlank
    private String slogan;
    @NotBlank
    @Size(max = 50)
    private String numero;
    @ManyToOne
    Structure structure;
}
