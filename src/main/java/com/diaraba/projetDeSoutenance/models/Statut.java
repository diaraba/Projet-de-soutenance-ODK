package com.diaraba.projetDeSoutenance.models;

import javax.persistence.*;
@Entity
@Table(name = "statuts")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatut name;

    public Statut() {

    }

    public Statut(EStatut name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EStatut getName() {
        return name;
    }

    public void setName(EStatut name) {
        this.name = name;
    }
}
