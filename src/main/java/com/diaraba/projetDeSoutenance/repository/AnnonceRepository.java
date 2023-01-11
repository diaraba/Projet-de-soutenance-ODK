package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
}
