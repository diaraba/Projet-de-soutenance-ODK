package com.diaraba.projetDeSoutenance.security.services.annonce;

import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.models.AvisOffre;
import com.diaraba.projetDeSoutenance.payload.request.AnnonceRequest;
import com.diaraba.projetDeSoutenance.payload.request.AvisOffreRequest;
import com.diaraba.projetDeSoutenance.payload.response.AnnonceResponse;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnonceServiceImpl implements  AnnonceService{
    @Autowired
    AnnonceRepository annonceRepository;

    private AnnonceRequest mapToDTO(Annonce annonce){
        AnnonceRequest annonceRequest=new AnnonceRequest();
        annonceRequest.setId(annonce.getId());
        annonceRequest.setDate(annonce.getDate());
        annonceRequest.setTitre(annonce.getTitre());
        annonceRequest.setContenu(annonce.getContenu());
        annonceRequest.setStructure(annonce.getStructure());
        annonceRequest.setImage(annonce.getImage());
        return annonceRequest;
    }
    @Override
    public ResponseEntity<?> creerAnnonce(Annonce annonce) {
        annonceRepository.save(annonce);
        return  ResponseEntity.ok(new MessageResponse("Annonce enregistrer avec success!"));
    }

    @Override
    public ResponseEntity<?> updateAnnonce(Long id, Annonce annonce) {
        annonce.setId(id);
        annonceRepository.save(annonce);
        return  ResponseEntity.ok(new MessageResponse("Annonce modifier avec success!"));

    }

    @Override
    public AnnonceResponse afficherAnnonce(int pageNo, int pageSize) {

        Pageable pageable= PageRequest.of(pageNo, pageSize);

        Page<Annonce> avisoffres=annonceRepository.findAll(pageable);

        List<Annonce> listAnnonce=avisoffres.getContent();


        List<AnnonceRequest> content =listAnnonce.stream().map(annonce -> mapToDTO(annonce)).collect(Collectors.toList());

        AnnonceResponse annonceResponse=new AnnonceResponse();
        annonceResponse.setContent(content);
        annonceResponse.setPageNo(avisoffres.getNumber());
        annonceResponse.setPageSize(avisoffres.getSize());
        annonceResponse.setTotalElments(avisoffres.getTotalElements());
        annonceResponse.setTotalPages(avisoffres.getTotalPages());
        annonceResponse.setLast(avisoffres.isLast());
        return annonceResponse;
    }
}
