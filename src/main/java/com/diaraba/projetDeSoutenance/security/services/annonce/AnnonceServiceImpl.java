package com.diaraba.projetDeSoutenance.security.services.annonce;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnnonceServiceImpl implements  AnnonceService{
    @Autowired
    AnnonceRepository annonceRepository;
    @Override
    public ResponseEntity<?> creerAnnonce(Annonce annonce) {
        annonceRepository.save(annonce);
        return  ResponseEntity.ok(new MessageResponse("Annonce enregistrer avec success!"));
    }
}
