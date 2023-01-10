package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/utilisateur")
public class UtlisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("afficherUtilisateur/{nomutilisateur}")
    public Utilisateurs afficherutilisateur(@PathVariable("nomutilisateur")String nomutilisateur){

        return utilisateurService.afficherUser(nomutilisateur);
    }

}
