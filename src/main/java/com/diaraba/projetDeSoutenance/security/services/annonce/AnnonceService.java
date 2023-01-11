package com.diaraba.projetDeSoutenance.security.services.annonce;

import com.diaraba.projetDeSoutenance.models.Annonce;
import org.springframework.http.ResponseEntity;

public interface AnnonceService {
    ResponseEntity<?> creerAnnonce(Annonce annonce);
}
