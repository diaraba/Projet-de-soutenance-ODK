package com.diaraba.projetDeSoutenance.models;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "profilestructure")
public class ProfileStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max =100)
    private String nom;


    private String activite;



    private String localisation;


    private String image;


    @Size(max =10000)
    private String description;


    private String slogan;

    @Size(max = 50)
    private String numero;
    @ManyToOne
    @JoinColumn(name = "structure_iduser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Structure structure;
}
