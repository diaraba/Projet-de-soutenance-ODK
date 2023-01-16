package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByUtilisateurs(Utilisateurs utilisateurs);
    List<Abonnement> findByStructure(Structure structure);
}
