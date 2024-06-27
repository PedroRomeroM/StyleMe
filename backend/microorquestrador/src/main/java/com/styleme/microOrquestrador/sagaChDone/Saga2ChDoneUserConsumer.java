package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import com.styleme.microOrquestrador.repository.ChDoneRepository;


@Component
public class Saga2ChDoneUserConsumer {

    @Autowired
    private Saga1chDoneChProducer prev;
    
    
    @Autowired
    private ChDoneRepository rep;   
    
    @RabbitListener(queues = "chdone-user-receive")
    public void receiveMsg(@Payload MensagemDTO msg) {
        if(msg.getMensagem() == null){
            rep.deleteById(msg.getSagaId());
            return;
        }
        
        prev.rollbackOrdem(msg);
        //Deu algum erro coloque código pra executar aqui
    }
    
}
