package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.DTO.UserChallenge2DTO;
import com.styleme.microOrquestrador.model.SagaChDone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import com.styleme.microOrquestrador.repository.ChDoneRepository;


@Component
public class Saga1chDoneChConsumer {

    @Autowired
    private Saga2ChDoneUserProducer next;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChDoneRepository rep;   
    
    @RabbitListener(queues = "chdone-ch-receive")
    public void receiveMsg(@Payload MensagemDTO msg) {
        if(msg.getMensagem() == null){
            //Recebe pelo returnObj -> Converte pro formato de inserir no bd direto
            UserChallenge2DTO dto = mapper.map(msg.getReturnObj(), UserChallenge2DTO.class);
            
            SagaChDone model = new SagaChDone();
            model.setIdChallenge(dto.getChallengeId());
            model.setIdUser(dto.getUserId());
            model.setScore(dto.getScore());
           
            model = rep.save(model);

            msg.setSagaId(model.getSagaId()); //Salva o id do saga
            
            msg.setSendObj(dto);
            msg.setReturnObj(null);
            
            next.commitOrdem(msg);
            return;
        }
        
        //Deu algum erro coloque código pra executar aqui
    }
    
}
