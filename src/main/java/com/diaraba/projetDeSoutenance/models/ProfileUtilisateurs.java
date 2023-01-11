package com.diaraba.projetDeSoutenance.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profileutilisateurs")
@Data
public class ProfileUtilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Email
    private String nom;

    @NotBlank
    @Size(max = 50)
    @Email
    private String prenom;

    @NotBlank
    @Size(max = 50)
    @Email
    private String genre;

    @NotBlank
    @Size(max = 50)
    @Email
    private String numero;

    @NotBlank
    @Size(max = 50)
    @Email
    private String situation;
    @ManyToOne
    Utilisateurs utilisateurs;
}
