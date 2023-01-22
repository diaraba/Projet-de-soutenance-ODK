package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Role;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.request.SignupRequest1;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest1;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.payload.response.SignupResponse1;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.RoleRepository;
import com.diaraba.projetDeSoutenance.repository.UserRepository;
import com.diaraba.projetDeSoutenance.repository.UtilisateurRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private SignupRequest1 mapToDTO(Utilisateurs utilisateur){
        SignupRequest1 signupRequest1=new SignupRequest1();
        signupRequest1.setNomutilisateur(utilisateur.getNomutilisateur());
        signupRequest1.setEmail(utilisateur.getEmail());
        signupRequest1.setActivites(utilisateur.getActivitesU());
        signupRequest1.setRole(utilisateur.getRoles());
        signupRequest1.setPassword(utilisateur.getPassword());
        signupRequest1.setIduser(utilisateur.getIduser());
        return signupRequest1;
    }


    @Override
    public ResponseEntity<?>  creerUtilisateur(Utilisateurs utilisateurs) {

        utilisateurRepository.save(utilisateurs);
        //mailSender.send(emailConstructor.constructNewUserEmail(utilisateurs));
      return ResponseEntity.ok(new MessageResponse("Utilisateur enregistrer avec success!")) ;

    }

    @Override
    public ResponseEntity<?> updateUtilisateur(Long id, Utilisateurs utilisateurs) {
        utilisateurs.setIduser(id);
        utilisateurRepository.save(utilisateurs);
        return ResponseEntity.ok(new MessageResponse("Utilisateur modifier avec success!")) ;

    }

    @Override
    public Utilisateurs afficherUser(String utilisateur) {
        return utilisateurRepository.findByNomutilisateur(utilisateur);
    }

    @Override
    public SignupResponse1 afficherAllUtilisateurs(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);

        Page<Utilisateurs> utilisateurs=utilisateurRepository.findAll(pageable);

        List<Utilisateurs> listUtilisateurs=utilisateurs.getContent();


        List<SignupRequest1> content =listUtilisateurs.stream().map(utilisateur -> mapToDTO(utilisateur)).collect(Collectors.toList());

        SignupResponse1 signupResponse1=new SignupResponse1();
        signupResponse1.setContent(content);
        signupResponse1.setPageNo(utilisateurs.getNumber());
        signupResponse1.setPageSize(utilisateurs.getSize());
        signupResponse1.setTotalElments(utilisateurs.getTotalElements());
        signupResponse1.setTotalPages(utilisateurs.getTotalPages());

        signupResponse1.setLast(utilisateurs.isLast());
        return signupResponse1;
    }


    @Override
    public ResponseEntity<?> resetMotdepasse(Utilisateurs utilisateurs) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        utilisateurs.setPassword(encryptedPassword);
        utilisateurRepository.save(utilisateurs);
        mailSender.send(emailConstructor.constructResetPasswordEmail(utilisateurs));

        return  ResponseEntity.ok(new MessageResponse("Mot de passe changer avec success!")) ;

    }
    @Override
    public ResponseEntity<?> updateMotdepasse(Utilisateurs utilisateurs, String nouveaumotdepasse) {
        String encryptedPassword = bCryptPasswordEncoder.encode(nouveaumotdepasse);
        utilisateurs.setPassword(encryptedPassword);
        utilisateurRepository.save(utilisateurs);
        mailSender.send(emailConstructor.constructUpdateUserProfileEmail(utilisateurs,nouveaumotdepasse));
        return  ResponseEntity.ok(new MessageResponse("Mot de passe changer avec success!")) ;

    }


}
