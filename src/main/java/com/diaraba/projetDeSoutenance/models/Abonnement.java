package com.diaraba.projetDeSoutenance.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "abonnement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Utilisateurs utilisateurs;

    @ManyToOne
    Structure structure;
}
