package com.diaraba.projetDeSoutenance.security.services.profileutilisateurs;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import org.springframework.http.ResponseEntity;

public interface ProfileUtilisateursService {
    ResponseEntity<?>creerProfileUtilisateurs(ProfileUtilisateurs profileUtilisateurs);
    ResponseEntity<?>updateProfileUtilisateurs(Long id,ProfileUtilisateurs profileUtilisateurs);
}
