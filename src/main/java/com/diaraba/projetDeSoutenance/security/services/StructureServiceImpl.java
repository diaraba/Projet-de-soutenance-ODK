package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest1;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.RoleRepository;
import com.diaraba.projetDeSoutenance.repository.StatutRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.utilis.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    private StructureRequest1 mapToDTO(Structure structure){
        StructureRequest1 structureRequest=new StructureRequest1();
        structureRequest.setAlias(structure.getAlias());
        structureRequest.setEmail(structure.getEmail());
        structureRequest.setActivites(structure.getActivites());
        structureRequest.setRole(structure.getRoles());
        structureRequest.setPassword(structure.getPassword());
        structureRequest.setStatut(structure.getStatuts());
        structureRequest.setIduser(structure.getIduser());
        return structureRequest;
    }
    @Override
    public ResponseEntity<?> creerStructure(Structure structure) {
        System.out.println("service "+structure.getActivites());
         structureRepository.save(structure);
         mailSender.send(emailConstructor.constructNewStructureEmail(structure));
        return ResponseEntity.ok(new MessageResponse("Structure registered successfully!")) ;
    }

    @Override
    public ResponseEntity<?> updateStructure(Long id, Structure structure) {
        structure.setIduser(id);


        structureRepository.save(structure);
        return ResponseEntity.ok(new MessageResponse("Structure modifier avec success!")) ;
    }

    @Override
    public Structure trouverStructureparalias(String alias) {
        return structureRepository.findByAlias(alias);
    }

    @Override
    public StructureResponse afficherAllStructure(int pageNo, int pageSize) {
       Pageable pageable= PageRequest.of(pageNo, pageSize);

        Page<Structure> structures=structureRepository.findAll(pageable);



        List<Structure> listStructures=structures.getContent();


         List<StructureRequest1> content =listStructures.stream().map(structure -> mapToDTO(structure)).collect(Collectors.toList());

        StructureResponse structureResponse=new StructureResponse();
        structureResponse.setContent(content);
        structureResponse.setPageNo(structures.getNumber());
        structureResponse.setPageSize(structures.getSize());
        structureResponse.setTotalElments(structures.getTotalElements());
        structureResponse.setTotalPages(structures.getTotalPages());
        structureResponse.setLast(structures.isLast());
        return structureResponse;
    }

    @Override
    public ResponseEntity<?> resetMotdepasse(Structure structure) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        structure.setPassword(encryptedPassword);
        structureRepository.save(structure);
        mailSender.send(emailConstructor.constructStructureResetPasswordEmail(structure));

        return  ResponseEntity.ok(new MessageResponse("Mot de passe changer avec success!")) ;
    }

    @Override
    public ResponseEntity<?> updateMotdepasse(Structure structure, String nouveaumotdepasse) {
        String encryptedPassword = bCryptPasswordEncoder.encode(nouveaumotdepasse);
        structure.setPassword(encryptedPassword);
        structureRepository.save(structure);
        mailSender.send(emailConstructor.constructStructureUpdateUserProfileEmail(structure, nouveaumotdepasse));
        return  ResponseEntity.ok(new MessageResponse("Mot de passe changer avec success!")) ;

    }
}
