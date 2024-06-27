
package com.styleme.microChallenge.rabbitmq;


import com.styleme.microChallenge.DTOs.MensagemDTO;
import com.styleme.microChallenge.service.ChallengeService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

@Component
public class ConsumerSagaDeleteChallenge {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ChallengeService serv;

    @Rollback
    @RabbitListener(queues = "chdelete-ch")
    public void checkifIsPossible(@Payload MensagemDTO msg) {
        Integer dto = mapper.map(msg.getSendObj(), Integer.class);
        try {
           serv.deleteUserChallengeByChId(dto);
           msg.setMensagem("challengeReady");
        } catch (Exception ex) {
           msg.setMensagem(null);
        }
        template.convertAndSend("chdelete-receive", msg);
    }
    
    @RabbitListener(queues = "chdelete-ch-2")
    public void commitOrdem(@Payload MensagemDTO msg) {
        try {
            Integer dto = mapper.map(msg.getSendObj(), Integer.class);
            serv.deleteUserChallengeByChId(dto);
        } catch (Exception ex) {
            System.out.println("Deu erro no Módulo Challenge ConsumerSagaDeleteChallenge: " + ex.getMessage());
        }
    }
    
}
