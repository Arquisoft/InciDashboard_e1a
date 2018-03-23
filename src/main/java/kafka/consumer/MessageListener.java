package kafka.consumer;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import entities.Incidence;
import services.IncidenceService;
import util.Util;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);
    
    @Autowired
    private IncidenceService incidenceService;

    @KafkaListener(topics = "exampleTopic")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");
        Incidence incidence = Util.createIncidence(data);		//Pasar a JSON y poner filtro valores peligrosos
    }



}
