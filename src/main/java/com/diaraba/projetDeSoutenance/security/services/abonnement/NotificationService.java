package com.diaraba.projetDeSoutenance.security.services.abonnement;

import com.diaraba.projetDeSoutenance.models.Abonnement;
import com.diaraba.projetDeSoutenance.models.Notification;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity<?> updatenotif(Notification notif, Long id);
}
