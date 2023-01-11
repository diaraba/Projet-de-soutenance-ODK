package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisOffreRepository extends JpaRepository<AvisOffre, Long> {

}
