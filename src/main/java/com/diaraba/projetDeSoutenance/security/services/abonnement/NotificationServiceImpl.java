package com.diaraba.projetDeSoutenance.security.services.abonnement;

import com.diaraba.projetDeSoutenance.models.Notification;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public ResponseEntity<?> updatenotif(Notification notif, Long id) {
        notif.setIdnotification(id);
        notificationRepository.save(notif);
        return ResponseEntity.ok(new MessageResponse("Notif modifier avec success!"));
    }
}
