package com.diaraba.projetDeSoutenance.payload.request;

import com.diaraba.projetDeSoutenance.models.Activites;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;
@Data
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String nomutilisateur;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;


  private List<Activites> activites= new ArrayList<>();


  public String getNomutilisateur() {
    return nomutilisateur;
  }

  public void setNomutilisateur(String nomutilisateur) {
    this.nomutilisateur = nomutilisateur;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
