package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.models.Demande;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByUtilisateurs(Utilisateurs utilisateurs);

}
