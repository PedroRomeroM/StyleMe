package com.styleme.microOrquestrador.sagaCadastro;

import com.styleme.microOrquestrador.DTO.AuthDTO;
import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import com.styleme.microOrquestrador.repository.ChDoneRepository;


@Component
public class Saga2AuthConsumer {

    @Autowired
    private Saga1UserProducer prev;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChDoneRepository rep;   
    
    @RabbitListener(queues = "cadastro-auth-receive")
    public void receiveMsg(@Payload MensagemDTO msg) {
        if(msg.getMensagem() == null){
            rep.deleteById(msg.getSagaId());
            return;
        }
        
        prev.rollbackOrdem(msg);
        //Deu algum erro coloque código pra executar aqui
    }
    
}
