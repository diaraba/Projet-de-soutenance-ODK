package com.diaraba.projetDeSoutenance.security.services.abonnement;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import org.springframework.http.ResponseEntity;

public interface AbonnementService {
    ResponseEntity<?> creerAbonnement(Abonnement abonnement);
}
