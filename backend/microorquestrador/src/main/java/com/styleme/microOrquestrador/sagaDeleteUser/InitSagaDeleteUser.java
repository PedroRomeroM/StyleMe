
package com.styleme.microOrquestrador.sagaDeleteUser;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaDeleteUser;
import com.styleme.microOrquestrador.repository.UserDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitSagaDeleteUser {
    
    @Autowired
    private Saga1DeleteUserProducer prod;
    
    @Autowired
    private UserDeleteRepository rep;
    
    public void deleteUser(Integer id){
        
        MensagemDTO msg = new MensagemDTO();
        msg.setSendObj(id);
        
        SagaDeleteUser model = new SagaDeleteUser();
        model.setSagaId(null);
        model.setUserReady(Boolean.FALSE);
        model.setChallengeReady(Boolean.FALSE);
        model = rep.save(model);
                
        msg.setSagaId(model.getSagaId());
            
        
        prod.commitOrdem(msg);
    }
    
}
