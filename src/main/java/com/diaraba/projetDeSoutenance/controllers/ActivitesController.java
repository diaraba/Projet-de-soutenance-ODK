package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Activites;
import com.diaraba.projetDeSoutenance.repository.ActivitesRepository;
import com.diaraba.projetDeSoutenance.security.services.activites.ActivitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/activites/")
public class ActivitesController {
    @Autowired
    ActivitesService activitesService;
    @Autowired
    ActivitesRepository activitesRepository;
    @PostMapping("creerActivites")
        public ResponseEntity<Object> creerActivites(@Param("nom") String nom){

        Activites activites=new Activites();
        activites.setNom(nom);
        return activitesService.creerActivite(activites);        
    }
    @GetMapping("afficher")
    public List<Activites> getAll(){
        return activitesRepository.findAll();
    }
}
