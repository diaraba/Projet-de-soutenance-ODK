package com.diaraba.projetDeSoutenance.security.services.activites;

import com.diaraba.projetDeSoutenance.models.Activites;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.ActivitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitesServiceImpl implements ActivitesService{
    @Autowired
    private ActivitesRepository activitesRepository;

    @Override
    public ResponseEntity<Object> creerActivite(Activites activite) {

        activitesRepository.save(activite);
        return ResponseEntity.ok(new MessageResponse("User enregistrer avec success!")) ;
    }

    @Override
    public List<Activites> getAll() {
        return activitesRepository.findAll();
    }
}
