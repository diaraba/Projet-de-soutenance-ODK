package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Role;
import com.diaraba.projetDeSoutenance.models.User;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import com.diaraba.projetDeSoutenance.payload.response.SignupResponse1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UtilisateurService {
ResponseEntity<?>  creerUtilisateur(Utilisateurs utilisateurs);

ResponseEntity<?>  updateUtilisateur(Long id,Utilisateurs utilisateurs);
Utilisateurs afficherUser(String utilisateur);
SignupResponse1 afficherAllUtilisateurs(int pageNo, int pageSize);

    ResponseEntity<?> updateMotdepasse(User utilisateurs, String nouveaumotdepasse);

    ResponseEntity<?> resetMotdepasse(Utilisateurs utilisateurs);
}
