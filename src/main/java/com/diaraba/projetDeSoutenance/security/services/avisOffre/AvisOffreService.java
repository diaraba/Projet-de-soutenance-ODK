package com.diaraba.projetDeSoutenance.security.services.avisOffre;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import org.springframework.http.ResponseEntity;

public interface AvisOffreService {
    ResponseEntity<?> creerStructure(AvisOffre avisOffre);

}
