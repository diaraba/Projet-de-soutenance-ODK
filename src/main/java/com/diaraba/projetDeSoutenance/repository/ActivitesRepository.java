package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Activites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitesRepository extends JpaRepository<Activites, Long> {
}
