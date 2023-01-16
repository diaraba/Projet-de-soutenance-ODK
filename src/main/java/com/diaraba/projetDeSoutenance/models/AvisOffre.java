package com.diaraba.projetDeSoutenance.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "avisoffre")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvisOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titre;

    @NotBlank
    private String image;

    @NotBlank
    @Size(max =10000)
    private String description;

    @NotBlank
    private String cible;



    private Date date;
    @NotBlank
    private String conditions;
    @ManyToOne
    Structure structure;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String titre;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String condition;

    @NotBlank
    @Size(max = 50)
    private String cible;

    @NotBlank
    @Size(max = 50)
    private String image;

    @NotBlank
    @Size(max = 50)
    private Date date;*/
   /* @ManyToOne
    Structure structure;*/
    @ManyToOne
    TypeOffre typeOffre;
}
