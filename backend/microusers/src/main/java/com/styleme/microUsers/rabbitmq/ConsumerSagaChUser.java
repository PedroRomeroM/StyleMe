
package com.styleme.microUsers.rabbitmq;

import com.styleme.microUsers.DTOs.MensagemDTO;
import com.styleme.microUsers.DTOs.UserChallengeDTO;
import com.styleme.microUsers.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSagaChUser {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private UsersService serv;

    @RabbitListener(queues = "chdone-user")
    public void commitOrdem(@Payload MensagemDTO msg) {
        UserChallengeDTO dto = mapper.map(msg.getSendObj(), UserChallengeDTO.class);
        try {
            dto = serv.insertChallenge(dto);
           msg.setReturnObj(dto);
        } catch (Exception ex) {
            msg.setMensagem(ex.getMessage());
        }
        template.convertAndSend("chdone-user-receive", msg);
    }

}
