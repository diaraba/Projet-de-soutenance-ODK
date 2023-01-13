package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.RoleRepository;
import com.diaraba.projetDeSoutenance.repository.StatutRepository;
import com.diaraba.projetDeSoutenance.repository.StructureRepository;
import com.diaraba.projetDeSoutenance.utilis.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Override
    public ResponseEntity<?> creerStructure(Structure structure) {
        System.out.println("service "+structure.getActivites());
         structureRepository.save(structure);
         mailSender.send(emailConstructor.constructNewStructureEmail(structure));
        return ResponseEntity.ok(new MessageResponse("Structure registered successfully!")) ;
    }

    @Override
    public Structure trouverStructureparalias(String alias) {
        return structureRepository.findByAlias(alias);
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
