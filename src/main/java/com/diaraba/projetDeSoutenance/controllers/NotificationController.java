package com.diaraba.projetDeSoutenance.controllers;

import com.diaraba.projetDeSoutenance.models.Notification;
import com.diaraba.projetDeSoutenance.repository.NotificationRepository;
import com.diaraba.projetDeSoutenance.security.services.abonnement.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notification/")
public class NotificationController {
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @Autowired
    NotificationService notificationService;
    @PutMapping("notificationvue/{id}")
    public ResponseEntity<?> notificationvue(@PathVariable Long id,
                                             @Param("etat") String etat,
                                             @Param("status") String status,
                                              @Param("contenu") String contenu,
                                             @Param("titre") String titre  ){
        Notification notification=notificationRepository.findByIdnotification(id);
        if(contenu== null|| contenu.trim().isEmpty()) {
            notification.setContenu(notification.getContenu());
        }else {
            notification.setContenu(contenu);
        }
        if(titre== null|| titre.trim().isEmpty()) {
            notification.setTitre(notification.getTitre());
        }else {
            notification.setTitre(titre);
        }

        if(etat== null|| etat.trim().isEmpty()) {
            notification.setEtat(notification.getEtat());
        }else {
            notification.setEtat(etat);
        }

        if(status== null|| status.trim().isEmpty()) {
            notification.setStatus(notification.getStatus());
        }else {
            notification.setStatus(status);
        }
        return notificationService.updatenotif(notification,id);
    }
}
