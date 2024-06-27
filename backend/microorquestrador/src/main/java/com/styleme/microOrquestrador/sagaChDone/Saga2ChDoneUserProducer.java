
package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Saga2ChDoneUserProducer {

    @Autowired
    private AmqpTemplate template;
    
    //2 da sequencia
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueChInsert.queueUserDoneCh, dto);
    }
    
    
}
