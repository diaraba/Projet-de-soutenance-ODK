package com.diaraba.projetDeSoutenance.security.services.profilestructure;

import com.diaraba.projetDeSoutenance.models.ProfileStructure;
import org.springframework.http.ResponseEntity;

public interface ProfileStructureService {
    ResponseEntity<?>creerProfileStructure(ProfileStructure profileStructure);
    ResponseEntity<?>updateProfileStructure(Long id,ProfileStructure profileStructure);
}
