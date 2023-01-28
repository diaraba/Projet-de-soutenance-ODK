package com.diaraba.projetDeSoutenance.security.services.avisOffre;

import com.diaraba.projetDeSoutenance.models.AvisOffre;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.payload.request.AvisOffreRequest;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest1;

import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.payload.response.StructureResponse;
import com.diaraba.projetDeSoutenance.repository.AvisOffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvisOffreServiceImpl implements AvisOffreService {
    @Autowired
    AvisOffreRepository avisOffreRepository;

    private AvisOffreRequest mapToDTO(AvisOffre avisOffre){
        AvisOffreRequest avisOffreRequest=new AvisOffreRequest();
        avisOffreRequest.setId(avisOffre.getIdavisoffre());
        avisOffreRequest.setDate(avisOffre.getDate());
        avisOffreRequest.setCible(avisOffre.getCible());
        avisOffreRequest.setTitre(avisOffre.getTitre());
        avisOffreRequest.setConditions(avisOffre.getConditions());
        avisOffreRequest.setImage(avisOffre.getImage());
        avisOffreRequest.setDescription(avisOffre.getDescription());
        return avisOffreRequest;
    }
    @Override
    public AvisOffre creerAvisOffre(AvisOffre avisOffre) {


        return avisOffreRepository.save(avisOffre);
    }

    @Override
    public ResponseEntity<?> updateAvisOffre(Long id, AvisOffre avisOffre) {
        avisOffre.setIdavisoffre(id);
        return ResponseEntity.ok(new MessageResponse("AvisOffre modifier avec success!"));
    }

    @Override
    public AvisOffreResponse afficherAllAvisOffre(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);

        Page<AvisOffre> avisoffres=avisOffreRepository.findAll(pageable);

        List<AvisOffre> listavisoffre=avisoffres.getContent();


        List<AvisOffreRequest> content =listavisoffre.stream().map(avisOffre -> mapToDTO(avisOffre)).collect(Collectors.toList());

        AvisOffreResponse avisOffreResponse=new AvisOffreResponse();
        avisOffreResponse.setContent(content);
        avisOffreResponse.setPageNo(avisoffres.getNumber());
        avisOffreResponse.setPageSize(avisoffres.getSize());
        avisOffreResponse.setTotalElments(avisoffres.getTotalElements());
        avisOffreResponse.setTotalPages(avisoffres.getTotalPages());
        avisOffreResponse.setLast(avisoffres.isLast());
        return avisOffreResponse;
    }
}
