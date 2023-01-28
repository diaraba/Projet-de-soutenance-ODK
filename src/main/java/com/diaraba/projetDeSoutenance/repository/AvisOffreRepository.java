package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import com.diaraba.projetDeSoutenance.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisOffreRepository extends JpaRepository<AvisOffre, Long> {
List<AvisOffre> findByStructure(Structure structure);
AvisOffre findByIdavisoffre(Long idavisoffre);
}
