
package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.DTO.UserChallengeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitSagaChDone {
    
    @Autowired
    private Saga1chDoneChProducer prod;
    
    public void insertChallengeUser(UserChallengeDTO dto){
        MensagemDTO msg = new MensagemDTO();
        msg.setSendObj(dto);
        
        prod.commitOrdem(msg);
    }
    
}
