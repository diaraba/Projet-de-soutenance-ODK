package com.diaraba.projetDeSoutenance.security.services.profileutilisateurs;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.ProfileUtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.profilestructure.ProfileStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileUtilisateursServiceImpl implements ProfileUtilisateursService {

    @Autowired
    ProfileUtilisateurRepository profileUtilisateurRepository;
    @Override
    public ResponseEntity<?> creerProfileUtilisateurs(ProfileUtilisateurs profileUtilisateurs) {

        profileUtilisateurRepository.save(profileUtilisateurs);
        return ResponseEntity.ok(new MessageResponse("Profile utilisateur enregistrer avec success!"));
    }

    @Override
    public ResponseEntity<?> updateProfileUtilisateurs(Long id, ProfileUtilisateurs profileUtilisateurs) {
        profileUtilisateurs.setIdutilisateur(id);
        profileUtilisateurRepository.save(profileUtilisateurs);
        return ResponseEntity.ok(new MessageResponse("Profile utilisateur modifier avec success!"));    }
}
