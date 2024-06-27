
package com.styleme.microChallenge.rabbitmq;


import com.styleme.microChallenge.DTOs.MensagemDTO;
import com.styleme.microChallenge.DTOs.UserChallenge2DTO;
import com.styleme.microChallenge.DTOs.UserChallengeDTO;
import com.styleme.microChallenge.service.ChallengeService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSagaUserChallenge {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ChallengeService serv;

    @RabbitListener(queues = "chdone-ch")
    public void commitOrdem(@Payload MensagemDTO msg) {
        UserChallengeDTO dto = mapper.map(msg.getSendObj(), UserChallengeDTO.class);
        try {
           UserChallenge2DTO ret = serv.insertUserChallenge(dto);
           msg.setReturnObj(ret);
        } catch (Exception ex) {
            msg.setMensagem(ex.getMessage());
        }
        template.convertAndSend("chdone-ch-receive", msg);
    }
    
    @RabbitListener(queues = "chdone-ch-rollback")
    public void rollbackOrdem(@Payload MensagemDTO msg) {
        try {
            UserChallengeDTO dto = mapper.map(msg.getSendObj(), UserChallengeDTO.class);
            serv.deleteUserChallenge(dto.getUserId(), dto.getChallengeId());
        } catch (Exception ex) {
            System.out.println("Deu erro no Módulo Challenge UserChallenge: " + ex.getMessage());
        }
    }
    
}
