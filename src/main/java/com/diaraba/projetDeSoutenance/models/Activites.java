package com.diaraba.projetDeSoutenance.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "activites")
@Data
public class Activites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @ManyToMany(mappedBy = "activites")
    private List<Structure> structures= new ArrayList<>();

    @ManyToMany(mappedBy = "activitesU")
    private List<Utilisateurs> utilisateurs= new ArrayList<>();
}
