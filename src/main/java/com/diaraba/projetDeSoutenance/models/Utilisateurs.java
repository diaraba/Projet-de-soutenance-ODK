package com.diaraba.projetDeSoutenance.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class Utilisateurs extends User{
    /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;*/

    @NotBlank
    @Size(max = 20)
    private String nomutilisateur;



    public Utilisateurs() {
    }

    public Utilisateurs(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

       /* public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }*/

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }




}