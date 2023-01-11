package com.diaraba.projetDeSoutenance.controllers;


import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/utilisateur")
public class StructureController {

    @Autowired
    StructureService structureService;

    @GetMapping("afficherStructure/{alias}")
    public Structure affichertructureparalias(@PathVariable("alias")String alias){
        return structureService.trouverStructureparalias(alias);
    }
}
