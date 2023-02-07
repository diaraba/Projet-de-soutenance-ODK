package com.diaraba.projetDeSoutenance.security.services.demande;

import com.diaraba.projetDeSoutenance.models.Demande;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.DemandeRepository;
import com.diaraba.projetDeSoutenance.utilis.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DemandeServiceImpl implements DemandeService {
    @Autowired
    private EmailConstructor emailConstructor;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    DemandeRepository demandeRepository;
    @Override
    public ResponseEntity<?> sendemaildemande(Demande demande) {
        demandeRepository.save(demande);
        mailSender.send(emailConstructor.sendmailfordemande(demande));
        return ResponseEntity.ok(new MessageResponse("Structure registered successfully!")) ;
    }
}
