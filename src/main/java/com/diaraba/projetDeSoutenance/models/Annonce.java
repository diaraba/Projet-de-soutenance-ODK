package com.diaraba.projetDeSoutenance.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "annonce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titre;

    @NotBlank
    private String objet;

    @NotBlank
    @Size(max =10000)
    private String contenu;


    private String image;

    private Date date;
    @ManyToOne
    Structure structure;
}
