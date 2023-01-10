package com.diaraba.projetDeSoutenance.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginStructureRequest {

    @NotBlank
    private String alias;

    @NotBlank
    private String password;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
