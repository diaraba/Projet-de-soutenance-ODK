package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
