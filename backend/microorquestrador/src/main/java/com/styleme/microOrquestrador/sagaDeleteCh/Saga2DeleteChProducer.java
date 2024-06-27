
package com.styleme.microOrquestrador.sagaDeleteCh;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Saga2DeleteChProducer {

    @Autowired
    private AmqpTemplate template;
       
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueDeleteCh.queueChDeleteCh2, dto);
        template.convertAndSend(ConfigQueueDeleteCh.queueChDeleteUs2, dto);
    }

}
