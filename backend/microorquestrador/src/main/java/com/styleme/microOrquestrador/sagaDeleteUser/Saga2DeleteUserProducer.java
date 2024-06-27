
package com.styleme.microOrquestrador.sagaDeleteUser;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Saga2DeleteUserProducer {

    @Autowired
    private AmqpTemplate template;
       
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueDeleteUser.queueUserDeleteCh2, dto);
        template.convertAndSend(ConfigQueueDeleteUser.queueUserDeleteUs2, dto);
    }

}
