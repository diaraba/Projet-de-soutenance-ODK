package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUtilisateurRepository extends JpaRepository<ProfileUtilisateurs, Long> {
    ProfileUtilisateurs findByUtilisateurs(Utilisateurs utilisateurs);
    ProfileUtilisateurs findByIdutilisateur(Long id);

}
