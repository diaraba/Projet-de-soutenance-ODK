package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.EStatut;
import com.diaraba.projetDeSoutenance.models.TypeOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOffreRepository extends JpaRepository<TypeOffre, Long> {
    TypeOffre findByNom(String nom);
}
