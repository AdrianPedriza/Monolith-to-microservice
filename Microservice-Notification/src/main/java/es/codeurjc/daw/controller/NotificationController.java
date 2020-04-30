package es.codeurjc.daw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Notification;
import es.codeurjc.daw.model.NotificationDto;
import es.codeurjc.daw.services.NotificationService;


@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public ResponseEntity<NotificationDto> newNotification(@RequestBody NotificationDto notificationDto) {
        this.notificationService.add(new Notification(notificationDto));
        return new ResponseEntity<>(notificationDto, HttpStatus.CREATED);
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable final Long id) {
        Optional<Notification> notification = this.notificationService.get(id);
        if(notification.isPresent()){
            return new ResponseEntity<>(notification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}