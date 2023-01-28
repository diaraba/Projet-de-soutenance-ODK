package com.diaraba.projetDeSoutenance.repository;

import com.diaraba.projetDeSoutenance.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByContenu(String contenu);
}
