package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Demande;
import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.ProfileUtilisateurRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.demande.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class DemandeController {
    private final UtilisateurRepository utilisateurRepository;

    public DemandeController(UtilisateurRepository utilisateurRepository,
                             StructureRepository structureRepository,
                             ProfileUtilisateurRepository profileUtilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.structureRepository = structureRepository;
        this.profileUtilisateurRepository = profileUtilisateurRepository;
    }

    @Autowired
    DemandeService demandeService;
    private final StructureRepository structureRepository;
    private final ProfileUtilisateurRepository profileUtilisateurRepository;

    @PostMapping("envoidelademande/{idutilisateur}/{idstructure}")
    public ResponseEntity<?> Envoidemande (@PathVariable Long idutilisateur,
                                           @PathVariable Long idstructure,
                                           @Param("nom") String nom,
                                           @Param("email") String email,
                                           @Param("numero") String numero,
                                           @Param("contenu") String contenu,
                                           @Param("objet") String objet,
                                           @Param("statut") String statut){
        Utilisateurs utilisateurs=utilisateurRepository.findByIduser(idutilisateur);
        Structure structure=structureRepository.findByIduser(idstructure);
        ProfileUtilisateurs profileUtilisateurs=profileUtilisateurRepository.findByUtilisateurs(utilisateurs);
        Demande demande=new Demande();
        demande.setNom(nom);
        demande.setNumero(numero);
        demande.setEmail(email);
        demande.setContenu(contenu);
        demande.setObjet(objet);
        demande.setEmaildestinateur(structure.getEmail());
        demande.setGenre(profileUtilisateurs.getGenre());
        demande.setStatut(statut);
        demande.setNomstructure(structure.getAlias());
        demande.setUtilisateurs(utilisateurs);
        demandeService.sendemaildemande(demande);
        return ResponseEntity.ok(new MessageResponse("Demande envoyer avec success!"));
    }
}
