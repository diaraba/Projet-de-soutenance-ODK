package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.ERole;
import com.diaraba.projetDeSoutenance.models.EStatut;
import com.diaraba.projetDeSoutenance.models.Role;
import com.diaraba.projetDeSoutenance.models.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StatutRepository  extends JpaRepository<Statut, Long> {
    Optional<Statut> findByName(EStatut name);
}
