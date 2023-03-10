package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.models.Annonce;
import com.diaraba.projetDeSoutenance.models.Notification;
import com.diaraba.projetDeSoutenance.models.Structure;
import com.diaraba.projetDeSoutenance.payload.response.AnnonceResponse;
import com.diaraba.projetDeSoutenance.payload.response.AvisOffreResponse;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.*;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.annonce.AnnonceService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.diaraba.projetDeSoutenance.utilis.constants.IMAGE_PATH;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/annonce")
public class AnnonceController {

    @Autowired
    AnnonceService annonceService;
    @Autowired
    StructureService structureService;
    @Autowired
    AnnonceRepository annonceRepository;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/creerAnnonce/{structure}")
    public ResponseEntity<?> creerAnnonce(@PathVariable Long structure,
                                          @Param("titre") String titre,
                                          @Param("contenu") String contenu,
                                          @Param("objet") String objet,
                                          @Param("image") MultipartFile image) throws IOException {
        Structure structure1 = structureRepository.findByIduser(structure);
        Annonce annonce=new Annonce();
        String img = StringUtils.cleanPath(image.getOriginalFilename());
        annonce.setImage(img);
        String uploaDir = IMAGE_PATH;
        ConfigImage.saveimg(uploaDir, img, image);

        annonce.setDate(new Date());
        annonce.setObjet(objet);
        annonce.setStructure(structureRepository.findByIduser(structure));
        annonce.setContenu(contenu);
        annonce.setTitre(titre);
        annonce=annonceService.creerAnnonce(annonce);
        //Notification


        Notification notification = new Notification();
        notification.setIdstructure(structure);
        notification.setIdtarget(annonce.getIdannonce());
        notification.setStatus("true");
        notification.setEtat("false");
        notification.setTarget("annonce");
        notification.setTitre(structure1.getAlias());
        notification.setContenu(" Cher utilisateur votre structure " + structure1.getAlias() + " vient de d'ajouter une nouvelle annonce");
        Notification notification1 = notificationRepository.save(notification);

        for (Abonnement abonnement :
                abonnementRepository.findByStructure(structure1)) {
            System.out.println(abonnement + "abonnementttttttttttttttttttttttt");
            abonnement.getUtilisateurs().getNotifications().add(notification1);

            utilisateurRepository.save(abonnement.getUtilisateurs());
        }

        return ResponseEntity.ok(new MessageResponse("Annonce enregistrer avec success!"));
    }

    @PutMapping("/modifierAnnonce/{id}/{structure}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Param("titre") String titre,
                                    @Param("contenu") String contenu,
                                    @Param("objet") String objet,
                                    @PathVariable Long structure,
                                    @Param("image") MultipartFile image) throws IOException {

        Annonce annonce=new Annonce();
        Annonce currentannonce= annonceRepository.findByIdannonce(id);
        if(image==null ) {
            String img =currentannonce.getImage();
            annonce.setImage(img);
        }else {
            String img = StringUtils.cleanPath(image.getOriginalFilename());
            String uploaDir =IMAGE_PATH;
            ConfigImage.saveimg(uploaDir, img, image);
            annonce.setImage(img);
        }

        if(titre==null) {
            System.out.println("higyfutugyfklkcytcy"+currentannonce.getTitre());
            annonce.setTitre(currentannonce.getTitre());
        } else if ( titre.trim().isEmpty() ) {
            annonce.setTitre(currentannonce.getTitre());
        } else {
            annonce.setTitre(titre);
        }

        if(contenu==null) {
            System.out.println("higyfutugyfklkcytcy"+currentannonce.getContenu());
            annonce.setContenu(currentannonce.getContenu());
        } else if ( contenu.trim().isEmpty() ) {
            annonce.setContenu(currentannonce.getContenu());
        } else {
            annonce.setContenu(contenu);
        }

        if(objet==null) {
            System.out.println("higyfutugyfklkcytcy"+currentannonce.getObjet());
            annonce.setObjet(currentannonce.getObjet());
        } else if ( objet.trim().isEmpty() ) {
            annonce.setObjet(currentannonce.getObjet());
        } else {
            annonce.setObjet(objet);
        }

        annonce.setDate(new Date());

        annonce.setStructure(structureRepository.findByIduser(structure));


        return annonceService.updateAnnonce(id,annonce);
    }

    @GetMapping("/afficherAllAnnonce")
    public AnnonceResponse afficherAllAnnonce(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false)int pageSize)
    {
        return annonceService.afficherAnnonce(pageNo,pageSize);
    }
    @GetMapping("afficherannonceparidstructure/{id}")
    public List<Annonce> afficherannonceparidstructure(@PathVariable Long id){
        Structure structure=new Structure();
        structure=structureRepository.findByIduser(id);
        return annonceRepository.findByStructure(structure);
    }
    @GetMapping("afficherannonceparid/{id}")
    public Annonce afficherannonceparid(@PathVariable Long id){
        System.out.println(id);
    return annonceRepository.findByIdannonce(id);
    }
    @DeleteMapping("supprimerannonce/{idannonce}")
    public ResponseEntity<?> supprimerannonce(@PathVariable Long idannonce){
        Annonce annonce=annonceRepository.findByIdannonce(idannonce);
        annonceRepository.delete(annonce);
        return ResponseEntity.ok(new MessageResponse("Annonce supprimer avec success!")) ;
    }

}
