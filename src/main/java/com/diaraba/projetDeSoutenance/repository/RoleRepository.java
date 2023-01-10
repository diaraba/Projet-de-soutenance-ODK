package com.diaraba.projetDeSoutenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diaraba.projetDeSoutenance.models.ERole;
import com.diaraba.projetDeSoutenance.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

  Optional<Role> findByName(String name);
}
