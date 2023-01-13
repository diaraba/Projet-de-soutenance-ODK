package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Activites;
import com.diaraba.projetDeSoutenance.security.services.activites.ActivitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class ActivitesController {
    @Autowired
    ActivitesService activitesService;
    @PostMapping("creerActivites")
        public ResponseEntity<Object> creerActivites(@Param("nom") String nom){

        Activites activites=new Activites();
        activites.setNom(nom);
        return activitesService.creerActivite(activites);        
    }
}
