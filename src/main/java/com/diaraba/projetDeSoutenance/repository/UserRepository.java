package com.diaraba.projetDeSoutenance.repository;

import java.util.Optional;

import com.diaraba.projetDeSoutenance.models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diaraba.projetDeSoutenance.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  //Optional<User> findByUsername(String username);




  Boolean existsByEmail(String email);

  User findByEmail(String email);
  User findByIduser(Long iduser);



}
