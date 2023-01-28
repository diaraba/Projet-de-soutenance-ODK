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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PostMapping("/ajouterAbonnement/{user}/{structure}")
    public ResponseEntity<?> ajouterAbonnement(@PathVariable Long user,
                                               @PathVariable Long structure){

            Abonnement abonnement=new Abonnement();
            System.out.println(user+"cmlz,vlknvefklvnelkvnelkvn efljkvlrznvkflvfkdk");

            abonnement.setUtilisateurs(utilisateurRepository.findByIduser(user));
            //System.out.println(abonnement.getUtilisateurs().getNomutilisateur()+"dkjfbcdkjbjkfdnckfjvbfkjvfbkfjvf");
            abonnement.setStructure(structureRepository.findByIduser(structure));
        return abonnementService.creerAbonnement(abonnement);
    }
    @GetMapping("/afficherabonnement/{user}")
    public List<Abonnement> afficher(@PathVariable Long user){
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

    @GetMapping("/afficherabonner/{structure}")
    public List<Abonnement> afficherabonner(@PathVariable Long structure){
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
    @GetMapping("/afficherpreference/{user}")
    public List<Structure> afficherpreference(@PathVariable Long user){
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
        System.out.println(listStructure);
        return  listStructure;
    }
}
