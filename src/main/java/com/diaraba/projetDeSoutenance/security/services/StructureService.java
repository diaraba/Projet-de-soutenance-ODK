package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StructureService {
    ResponseEntity<?> creerStructure(Structure structure);
    Structure trouverStructureparalias(String alias);

    ResponseEntity<?> resetMotdepasse(Structure structure);
    ResponseEntity<?> updateMotdepasse(Structure structure,String nouveaumotdepasse);


}
