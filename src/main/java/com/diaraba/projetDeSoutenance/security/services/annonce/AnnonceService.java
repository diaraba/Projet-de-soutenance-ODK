package com.diaraba.projetDeSoutenance.security.services.annonce;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.payload.response.AnnonceResponse;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import org.springframework.http.ResponseEntity;

public interface AnnonceService {
    ResponseEntity<?> creerAnnonce(Annonce annonce);
    ResponseEntity<?> updateAnnonce(Long id,Annonce annonce);

    AnnonceResponse afficherAnnonce(int pageNo, int pageSize);
}
