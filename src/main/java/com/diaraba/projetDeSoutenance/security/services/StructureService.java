package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest1;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StructureService {
    ResponseEntity<?> creerStructure(Structure structure);

    ResponseEntity<?> updateStructure(Long id,Structure structure);
    Structure trouverStructureparalias(String alias);
    StructureResponse afficherAllStructure(int pageNo, int pageSize);


    ResponseEntity<?> resetMotdepasse(Structure structure);
    ResponseEntity<?> updateMotdepasse(Structure structure,String nouveaumotdepasse);


}
