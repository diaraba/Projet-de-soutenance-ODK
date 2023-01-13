package com.diaraba.projetDeSoutenance.payload.request;

import com.diaraba.projetDeSoutenance.models.Activites;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
public class StructureRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String alias;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> statut;

    private Set<String> role;

    private List<Activites> activites= new ArrayList<>();

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public Set<String> getStatut() {
        return this.statut;
    }

    public void setStatut(Set<String> statut) {
        this.statut = statut;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
