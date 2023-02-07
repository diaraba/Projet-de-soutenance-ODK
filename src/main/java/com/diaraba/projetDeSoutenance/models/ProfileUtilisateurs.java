package com.diaraba.projetDeSoutenance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long idutilisateur;

    private String nom;


    private String prenom;


    private String genre;

    private String etat;

    private String numero;


    @Size(max =10000)
    private String situation;
    private String image;
    @ManyToOne
    Utilisateurs utilisateurs;
}
