package com.diaraba.projetDeSoutenance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "activites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Activites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nom;
    @JsonIgnore
    @ManyToMany(mappedBy = "activites")
    private List<Structure> structures= new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "activitesU")
    private List<Utilisateurs> utilisateurs= new ArrayList<>();
}
