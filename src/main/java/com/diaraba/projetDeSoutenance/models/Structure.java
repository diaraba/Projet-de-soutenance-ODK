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
@Data
@Table(name = "Structure"
       /* ,uniqueConstraints = {
                @UniqueConstraint(columnNames = "alias"),
                @UniqueConstraint(columnNames = "email")
        }*/)
public class Structure extends User{
    /*    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;*/

        @NotBlank
        @Size(max = 20)
        private String alias;
/*
        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        @NotBlank
        @Size(max = 120)
        private String password;*/

       @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(  name = "structure_statuts",
                joinColumns = @JoinColumn(name = "structure_id"),
                inverseJoinColumns = @JoinColumn(name = "statut_id"))
        private Set<Statut> statuts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "structure_activite",
            joinColumns = @JoinColumn(name="id_structure"),
            inverseJoinColumns = @JoinColumn(name = "id_activite"))
    private List<Activites> activites= new ArrayList<>();

        public Structure() {
        }

        public Structure(String alias) {
                this.alias = alias;
        }

       /* public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }*/

        public String getAlias() {
                return alias;
        }

        public void setAlias(String alias) {
                this.alias = alias;
        }


        public Set<Statut> getStatuts() {
                return statuts;
        }

        public void setStatuts(Set<Statut> Statuts) {
                this.statuts = Statuts;
        }





}
