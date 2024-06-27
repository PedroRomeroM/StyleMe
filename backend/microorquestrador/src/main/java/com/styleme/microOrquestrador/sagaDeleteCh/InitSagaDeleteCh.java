
package com.styleme.microOrquestrador.sagaDeleteCh;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.DTO.UserChallengeDTO;
import com.styleme.microOrquestrador.model.SagaDeleteCh;
import com.styleme.microOrquestrador.repository.ChDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitSagaDeleteCh {
    
    @Autowired
    private Saga1DeleteChProducer prod;
    
    @Autowired
    private ChDeleteRepository rep;
    
    public void deleteChallenge(Integer id){
        
        
        MensagemDTO msg = new MensagemDTO();
        msg.setSendObj(id);
        
        SagaDeleteCh model = new SagaDeleteCh();
        model.setSagaId(null);
        model.setUserReady(Boolean.FALSE);
        model.setChallengeReady(Boolean.FALSE);
        model = rep.save(model);
                
        msg.setSagaId(model.getSagaId());
            
        
        prod.commitOrdem(msg);
    }
    
}
