
package com.styleme.microOrquestrador.sagaDeleteUser;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Saga1DeleteUserProducer {

    @Autowired
    private AmqpTemplate template;
       
    //Primeiro da sequencia
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueDeleteUser.queueUserDeleteCh, dto);
        template.convertAndSend(ConfigQueueDeleteUser.queueUserDeleteUs, dto);
    }

}
