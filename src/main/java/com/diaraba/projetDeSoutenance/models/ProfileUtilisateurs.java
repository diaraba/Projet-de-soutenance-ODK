package com.diaraba.projetDeSoutenance.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profileutilisateurs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileUtilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String genre;

    @NotBlank
    private String numero;

    @NotBlank
    @Size(max =10000)
    private String situation;

    private String image;
    @ManyToOne
    Utilisateurs utilisateurs;
}
