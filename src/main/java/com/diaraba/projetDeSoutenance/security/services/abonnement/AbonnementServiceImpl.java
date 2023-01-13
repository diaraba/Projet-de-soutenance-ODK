package com.diaraba.projetDeSoutenance.security.services.abonnement;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AbonnementServiceImpl implements AbonnementService{
    @Autowired
    AbonnementRepository abonnementRepository;

    @Override
    public ResponseEntity<?> creerAbonnement(Abonnement abonnement) {
        abonnementRepository.save(abonnement);
        return ResponseEntity.ok(new MessageResponse("Abonnement enregistrer avec success!")) ;
    }
}
