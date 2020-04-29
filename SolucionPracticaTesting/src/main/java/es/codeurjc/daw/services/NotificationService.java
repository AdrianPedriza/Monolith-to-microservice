package es.codeurjc.daw.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.daw.model.Notification;

@Service
public class NotificationService {

    private final static Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    final String URI_NOTIFICATION_SERVICE = "http://localhost:8082/api/notification";

    @Value("${notification.microoservice}")
    private boolean notificationToMicroService;
    
    public void notify(Notification notification){
        if (notificationToMicroService){
            this.notificateFromMicroservice(notification);
        } else{
            this.notificateFromMonolith();
        }
    }

    private void notificateFromMicroservice(Notification notification) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(URI_NOTIFICATION_SERVICE, notification);
    }

    private void notificateFromMonolith() {
        LOGGER.info("Credito actualizado.");
    }
}