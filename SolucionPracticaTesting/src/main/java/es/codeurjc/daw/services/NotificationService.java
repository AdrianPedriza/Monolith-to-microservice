package es.codeurjc.daw.services;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final static Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    
    public void notify(String message){
        LOGGER.info(message);
    }
}