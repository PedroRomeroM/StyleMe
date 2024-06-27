
package com.styleme.microUsers.rabbitmq;

import com.styleme.microUsers.DTOs.MensagemDTO;
import com.styleme.microUsers.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

@Component
public class ConsumerSagaDeleteUser {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private UsersService serv;

    @Rollback
    @RabbitListener(queues = "userdelete-us")
    public void checkIfIsPossible(@Payload MensagemDTO msg) {
        Integer dto = mapper.map(msg.getSendObj(), Integer.class);
        try {
           serv.deleteUser(dto);
           msg.setMensagem("userReady");
        } catch (Exception ex) {
            msg.setMensagem(null);
        }
        template.convertAndSend("userdelete-receive", msg);
    }
    
    @RabbitListener(queues = "userdelete-us-2")
    public void commitOrdem(@Payload MensagemDTO msg) {
        try{
            Integer dto = mapper.map(msg.getSendObj(), Integer.class);
            serv.deleteUser(dto);
        } catch (Exception ex) {
            System.out.println("Deu erro no Módulo User ConsumerSagaDeleteUser: " + ex.getMessage());
        }
    }

}
