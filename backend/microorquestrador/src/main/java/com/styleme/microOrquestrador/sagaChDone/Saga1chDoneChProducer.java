
package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.DTO.UserChallengeDTO;
import com.styleme.microOrquestrador.model.SagaChDone;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.styleme.microOrquestrador.repository.ChDoneRepository;

@Component
public class Saga1chDoneChProducer {

    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChDoneRepository rep;
    
    
    //Primeiro da sequencia
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueChInsert.queueChDoneCh, dto);
    }

    
    public void rollbackOrdem(MensagemDTO msg) {
        if(msg.getSagaId() != null){
            Optional<SagaChDone> model = rep.findById(msg.getSagaId());
            if(model.isPresent()){
                UserChallengeDTO dto = new UserChallengeDTO();
                dto.setChallengeId(model.get().getIdChallenge());
                dto.setUserId(model.get().getIdUser());
                
                msg.setSendObj(dto);
                template.convertAndSend(ConfigQueueChInsert.queueChDoneChRollback, msg);
                //Faz alguma coisa
                rep.deleteById(msg.getSagaId());
            }else{
                System.err.println("Id Não Existe - Rollback de Saga1ChDoneProducer");
            }
        }else{
            System.err.println("Id não pode ser Null - Rollback de Saga1ChDoneProducer");
        }
    }
    
}
