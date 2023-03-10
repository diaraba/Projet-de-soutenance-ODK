package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {
    boolean existsByEmail(String email);
    boolean existsByNomutilisateur(String email);

    Utilisateurs findByNomutilisateur(String nomutilisateur);

    Utilisateurs findByEmail(String email);
    Utilisateurs findByIduser(Long iduser);

}
