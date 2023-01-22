package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import com.diaraba.projetDeSoutenance.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileStructureRepository extends JpaRepository<ProfileStructure, Long> {

    ProfileStructure findByStructure(Structure structure);
}
