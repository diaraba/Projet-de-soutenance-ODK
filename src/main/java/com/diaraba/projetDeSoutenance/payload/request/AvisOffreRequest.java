package com.diaraba.projetDeSoutenance.payload.request;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class AvisOffreRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String titre;

    @NotBlank
    @Size(max = 50)
    private String image;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String cible;

    @NotBlank
    @Size(max = 50)
    private Date date;
    @NotBlank
    @Size(max = 50)
    private String conditions;
}
