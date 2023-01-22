package com.diaraba.projetDeSoutenance.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "profilestructure")
public class ProfileStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max =100)
    private String nom;

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
