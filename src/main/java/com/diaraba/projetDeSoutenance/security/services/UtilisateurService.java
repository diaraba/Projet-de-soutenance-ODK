package com.diaraba.projetDeSoutenance.security.services;

import com.diaraba.projetDeSoutenance.models.Role;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UtilisateurService {
ResponseEntity<?>  creerUtilisateur(Utilisateurs utilisateurs);
Utilisateurs afficherUser(String utilisateur);

}
