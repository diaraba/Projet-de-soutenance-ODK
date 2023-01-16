package com.diaraba.projetDeSoutenance.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "statuts")
@AllArgsConstructor
@Getter
@Setter
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
