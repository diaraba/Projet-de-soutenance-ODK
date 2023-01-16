package com.diaraba.projetDeSoutenance.security.services.avisOffre;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import org.springframework.http.ResponseEntity;

public interface AvisOffreService {
    ResponseEntity<?> creerAvisOffre(AvisOffre avisOffre);
    ResponseEntity<?> updateAvisOffre(Long id, AvisOffre avisOffre);

    AvisOffreResponse afficherAllAvisOffre(int pageNo, int pageSize);

}
