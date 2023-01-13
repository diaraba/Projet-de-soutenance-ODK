package com.diaraba.projetDeSoutenance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AbonnementController {
    @PostMapping("ajouterAbonnement/")
    public ResponseEntity<?> ajouterAbonnement(@Param("user") Long user,
                                               @Param("structure") Long structure){


        return null;
    }
}
