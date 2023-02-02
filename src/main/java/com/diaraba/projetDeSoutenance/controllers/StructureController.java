package com.diaraba.projetDeSoutenance.controllers;


import com.diaraba.projetDeSoutenance.models.EStatut;
import com.diaraba.projetDeSoutenance.models.Statut;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.StatutRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/utilisateur")
public class StructureController {

    @Autowired
    StructureService structureService;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    StatutRepository statutRepository;

   /* @GetMapping("afficherStructure/{alias}")
    public List<Structure> affichertructureparalias(@PathVariable("alias")String alias){

        Set<String> strStatuts = Collections.singleton(alias);

        Set<Statut> statuts = new HashSet<>();


        if (strStatuts == null) {
            Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
            statuts.add(userStatut);
        } else {
            strStatuts.forEach(role -> {
                switch (role) {
                    case "prive":
                        Statut adminStatut = statutRepository.findByName(EStatut.Prive).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        statuts.add(adminStatut);

                        break;
                    case "public":
                        Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
                        statuts.add(userStatut);
                }
            });
        }

        return structureRepository.findByStatuts(statuts);
    }*/
    @GetMapping("/afficherAllStructure")
    public StructureResponse afficherAllStructure(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize)
    {
        return structureService.afficherAllStructure(pageNo,pageSize);
    }



    @GetMapping("/afficherAllStructurestatus")
    public ArrayList<List<Structure>> afficherAllStructureStatus()
    {
        System.out.println("jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");
        List<Structure> structureList=structureRepository.findAll();
        List<Structure> structureprive=new ArrayList<>();
        List<Structure> structurepublic=new ArrayList<>();
        System.out.println("jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");
       ArrayList<List<Structure>> structureListfinal=new ArrayList<>();
        System.out.println(structureList+"  jhvbeiuvmbbbbbbbbbkekkkkkkkkkkkkkkkkkkkkkkk");
        for (Structure structure:structureList) {
            System.out.println("jhvbeiuvmbbbbbbbbbkejjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            Statut statut=statutRepository.findByName(EStatut.Prive).get();
            if (structure.getStatuts().contains(statut)){
                structureprive.add(structure);
                System.out.println(structure +"jhvbeiuvmbbbbbbbbbkeiiiiiiiiiiiiiiiiiiiiiiiiii");
            }
            else {
                structurepublic.add(structure);
                System.out.println(structure +"jhvbeiuvmbbbbbbbbbkewwwwwwwwwwwwwwwwwwwwwwww");
            }
        }
        structureListfinal.add(structureprive);
        structureListfinal.add(structurepublic);

        return structureListfinal;
    }

}
