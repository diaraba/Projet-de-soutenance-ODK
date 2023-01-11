package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Role;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.RoleRepository;
import com.diaraba.projetDeSoutenance.repository.UserRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
import com.diaraba.projetDeSoutenance.utilis.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UtilisateurServiceImpl implements  UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    JavaMailSender mailSender;


    @Override
    public ResponseEntity<?>  creerUtilisateur(Utilisateurs utilisateurs) {

        utilisateurRepository.save(utilisateurs);
        mailSender.send(emailConstructor.constructNewUserEmail(utilisateurs));
      return ResponseEntity.ok(new MessageResponse("User enregistrer avec success!")) ;

    }

    @Override
    public Utilisateurs afficherUser(String utilisateur) {
        return utilisateurRepository.findByNomutilisateur(utilisateur);
    }


}
