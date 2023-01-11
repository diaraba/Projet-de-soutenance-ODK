package com.diaraba.projetDeSoutenance.models;

import javax.persistence.*;

@Entity
@Table(name = "abonnement")
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Utilisateurs utilisateurs;

    @ManyToOne
    Structure structure;
}
