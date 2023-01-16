package com.diaraba.projetDeSoutenance.payload.request;

import com.diaraba.projetDeSoutenance.models.Statut;
import com.diaraba.projetDeSoutenance.models.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceRequest {
    private Long id;

    @NotBlank
    private String titre;

    @NotBlank
    private String objet;

    @NotBlank
    @Size(max =10000)
    private String contenu;


    private String image;

    private Date date;
    private Structure structure;

}
