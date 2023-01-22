package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
  Structure findByAlias(String alias);

    Boolean existsByAlias(String alias);

    Boolean existsByEmail(String email);
    Structure findByIduser(Long iduser);

    //List<Structure> findByStatuts(Set<Statut> statuts);



}
