package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
}
