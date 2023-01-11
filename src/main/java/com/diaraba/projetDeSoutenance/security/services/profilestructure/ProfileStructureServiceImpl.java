package com.diaraba.projetDeSoutenance.security.services.profilestructure;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.ProfileStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileStructureServiceImpl implements ProfileStructureService{
    @Autowired
    ProfileStructureRepository profileStructureRepository;
    @Override
    public ResponseEntity<?> creerProfileStructure(ProfileStructure profileStructure) {
        profileStructureRepository.save(profileStructure);
        return ResponseEntity.ok(new MessageResponse("Profile structure enregistrer avec success!")) ;
    }
}
