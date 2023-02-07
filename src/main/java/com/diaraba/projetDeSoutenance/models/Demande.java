package com.diaraba.projetDeSoutenance.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "demande")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idannonce;


    private String nom;


    private String email;


    private String numero;
    @Size(max =10000)
    private String contenu;

    private String objet;

    private String emaildestinateur;
    private String statut;
    private String genre;
    private String nomstructure;


    @ManyToOne
    Utilisateurs utilisateurs;
}
