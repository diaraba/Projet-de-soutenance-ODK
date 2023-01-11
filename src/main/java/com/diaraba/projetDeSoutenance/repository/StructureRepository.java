package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.EStatut;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.User;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
  Structure findByAlias(String alias);

    Boolean existsByAlias(String alias);

    Boolean existsByEmail(String email);



}
