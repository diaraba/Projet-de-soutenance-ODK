package com.diaraba.projetDeSoutenance.security.services.demande;

import com.diaraba.projetDeSoutenance.models.Demande;
import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import org.springframework.http.ResponseEntity;

public interface DemandeService {
    ResponseEntity<?> sendemaildemande(Demande demande);
}
