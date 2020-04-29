package es.codeurjc.daw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Notification;
import es.codeurjc.daw.repositories.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll(){
        return this.notificationRepository.findAll();
    }

    public Optional<Notification> get(Long id){
        return this.notificationRepository.findById(id);
    }

	public void add(Notification notification) {
        this.notificationRepository.save(notification);
        System.out.println(String.format("Added to customer {0} the amount of {1}", notification.getCustomerId(), notification.getAmount()));
	}
}