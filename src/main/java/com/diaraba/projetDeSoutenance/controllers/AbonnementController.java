package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.models.Activites;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.repository.AbonnementRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.repository.UserRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.security.services.abonnement.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AbonnementController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    AbonnementService abonnementService;
    @Autowired
    AbonnementRepository abonnementRepository;
    @PostMapping("/ajouterAbonnement")
    public ResponseEntity<?> ajouterAbonnement(@Param("user") Long user,
                                               @Param("structure") Long structure){

    Abonnement abonnement=new Abonnement();

            abonnement.setUtilisateurs(utilisateurRepository.findByIduser(user));
            abonnement.setStructure(structureRepository.findByIduser(structure));
        return abonnementService.creerAbonnement(abonnement);
    }
    @GetMapping("/afficherabonnement")
    public List<Abonnement> afficher(@Param("user") Long user){
        Utilisateurs utilisateurs=utilisateurRepository.findByIduser(user);
        System.out.println(utilisateurs+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        List<Abonnement> list= abonnementRepository.findByUtilisateurs(utilisateurs);
        System.out.println(list);
        List<Structure> listStructure= new ArrayList<>();
       for(Abonnement abonnement:list){
            listStructure.add(abonnement.getStructure());
            System.out.println(abonnement);
        }
       return  list;
    }

    @GetMapping("/afficherabonner")
    public List<Abonnement> afficherabonner(@Param("structure") Long structure){
        Structure structure1=structureRepository.findByIduser(structure);
        System.out.println(structure1+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        List<Abonnement> list= abonnementRepository.findByStructure(structure1);
        System.out.println(list);
        List<Utilisateurs> listUtilisateurs= new ArrayList<>();
        for(Abonnement abonnement:list){
            listUtilisateurs.add(abonnement.getUtilisateurs());
            System.out.println(abonnement);
        }
        return  list;
    }
    @GetMapping("/afficherpreference")
    public List<Structure> afficherpreference(@Param("user") Long user){
        Utilisateurs utilisateurs=utilisateurRepository.findByIduser(user);
        System.out.println(utilisateurs+"11111111111111111111111111");
        List<Activites> listActivites= utilisateurs.getActivitesU();
        System.out.println(listActivites+"000000000000000000000000");
        List<Structure> listStructure= new ArrayList<>();
        List<Structure> list= new ArrayList<>();
        for(Activites activites:listActivites){
            listStructure=activites.getStructures();

            System.out.println(listActivites);
        }
        return  listStructure;
    }
}
