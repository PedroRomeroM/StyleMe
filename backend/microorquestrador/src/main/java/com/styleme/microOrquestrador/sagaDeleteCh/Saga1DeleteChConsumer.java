package com.styleme.microOrquestrador.sagaDeleteCh;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaDeleteCh;
import com.styleme.microOrquestrador.repository.ChDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;


@Component
public class Saga1DeleteChConsumer {

    @Autowired
    private Saga2DeleteChProducer next;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChDeleteRepository rep;   
    
    @RabbitListener(queues = "chdelete-receive")
    public void receiveMsg(@Payload MensagemDTO msg) {
        System.out.println("com==: " + msg.getMensagem());
        if(msg.getMensagem() != null){
            
            SagaDeleteCh model = rep.findById(msg.getSagaId()).get();            
            
            if(msg.getMensagem().equals("userReady")){
                model.setUserReady(Boolean.TRUE);
                model = rep.save(model);
            }else if(msg.getMensagem().equals("challengeReady")){
                model.setChallengeReady(Boolean.TRUE);
                model = rep.save(model);
            }
            
            msg.setMensagem(null);
            
            if(model.getChallengeReady() && model.getUserReady()){
                next.commitOrdem(msg);
                rep.deleteById(model.getSagaId());
            }
            return;
        }
        
        //Deu algum erro coloque código pra executar aqui
        System.err.println("A mensagem não pode estar null! - Rollback de Saga1DeleteChConsumer");
    }
    
}
