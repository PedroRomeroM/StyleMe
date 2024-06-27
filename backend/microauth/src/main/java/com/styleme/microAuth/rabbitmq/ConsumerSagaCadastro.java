
package com.styleme.microAuth.rabbitmq;

import com.styleme.microAuth.DTOs.AuthDTO;
import com.styleme.microAuth.DTOs.MensagemDTO;
import com.styleme.microAuth.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSagaCadastro {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private AuthService serv;

    @RabbitListener(queues = "cadastro-auth")
    public void commitOrdem(@Payload MensagemDTO msg) {
        AuthDTO dto = mapper.map(msg.getSendObj(), AuthDTO.class);
        try {
           serv.insertAuth(dto);
        } catch (Exception ex) {
            msg.setMensagem(ex.getMessage());
        }
        template.convertAndSend("cadastro-auth-receive", msg);
    }
    
}
