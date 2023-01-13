package com.diaraba.projetDeSoutenance.security.services.activites;

import com.diaraba.projetDeSoutenance.models.Activites;
import org.springframework.http.ResponseEntity;

public interface ActivitesService {
    ResponseEntity<Object> creerActivite(Activites activite);
}
