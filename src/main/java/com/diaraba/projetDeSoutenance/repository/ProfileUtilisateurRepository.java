package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.ProfileUtilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUtilisateurRepository extends JpaRepository<ProfileUtilisateurs, Long> {
}
