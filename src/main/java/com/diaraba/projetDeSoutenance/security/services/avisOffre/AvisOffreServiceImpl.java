package com.diaraba.projetDeSoutenance.security.services.avisOffre;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.AvisOffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvisOffreServiceImpl implements AvisOffreService {
    @Autowired
    AvisOffreRepository avisOffreRepository;
    @Override
    public ResponseEntity<?> creerStructure(AvisOffre avisOffre) {

        avisOffreRepository.save(avisOffre);
        return ResponseEntity.ok(new MessageResponse("AvisOffre créer avec success!"));
    }
}
