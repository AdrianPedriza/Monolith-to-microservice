package es.codeurjc.daw.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}