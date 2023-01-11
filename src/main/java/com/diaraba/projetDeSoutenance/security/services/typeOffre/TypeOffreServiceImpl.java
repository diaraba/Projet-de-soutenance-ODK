package com.diaraba.projetDeSoutenance.security.services.typeOffre;

import com.diaraba.projetDeSoutenance.models.TypeOffre;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.TypeOffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeOffreServiceImpl implements TypeOffreService {
    @Autowired
    TypeOffreRepository typeOffreRepository;
    @Override
    public ResponseEntity<?> creerTypeOffre(TypeOffre typeOffre) {
        typeOffreRepository.save(typeOffre);
        return  ResponseEntity.ok(new MessageResponse("Type d'avisoffre  enregistrer avec success!"));
    }

    @Override
    public TypeOffre trouverTypeOffreParNom(String typeoffre) {
        return typeOffreRepository.findByNom(typeoffre);
    }
}
