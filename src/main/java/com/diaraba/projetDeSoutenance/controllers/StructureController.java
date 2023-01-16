package com.diaraba.projetDeSoutenance.controllers;


import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/afficherAllStructure")
    public StructureResponse afficherAllStructure(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false)int pageSize)
    {
        return structureService.afficherAllStructure(pageNo,pageSize);
    }
}
