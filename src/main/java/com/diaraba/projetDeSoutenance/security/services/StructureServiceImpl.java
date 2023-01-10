package com.diaraba.projetDeSoutenance.security.services;

import antlr.BaseAST;
import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.RoleRepository;
import com.diaraba.projetDeSoutenance.repository.StatutRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StructureServiceImpl implements StructureService{
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StatutRepository statutRepository;
    @Override
    public ResponseEntity<?> creerStructure(Structure structure) {
         structureRepository.save(structure);
        return ResponseEntity.ok(new MessageResponse("Structure registered successfully!")) ;
    }

    @Override
    public Optional<Structure> trouverStructureparalias(String alias) {
        return structureRepository.findByAlias(alias);
    }
}
