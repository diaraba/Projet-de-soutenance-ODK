package com.diaraba.projetDeSoutenance.security.services.typeOffre;

import com.diaraba.projetDeSoutenance.models.TypeOffre;
import org.springframework.http.ResponseEntity;

public interface TypeOffreService {
    ResponseEntity<?> creerTypeOffre(TypeOffre typeOffre);
    TypeOffre trouverTypeOffreParNom(String typeoffre);
}
