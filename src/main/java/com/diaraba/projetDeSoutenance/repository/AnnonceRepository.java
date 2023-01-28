package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByStructure(Structure structure);
    Annonce findByIdannonce(Long idannonce);
}
