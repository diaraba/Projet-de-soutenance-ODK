package com.diaraba.projetDeSoutenance.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
@Data
public class Utilisateurs extends User{
    /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;*/

    @NotBlank
    @Size(max = 50)
    private String nomutilisateur;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "utilisateurs_activite",
            joinColumns = @JoinColumn(name="id_structure"),
            inverseJoinColumns = @JoinColumn(name = "id_activite"))
    private List<Activites> activitesU= new ArrayList<>();


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