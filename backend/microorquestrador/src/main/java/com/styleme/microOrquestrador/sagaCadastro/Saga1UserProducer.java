
package com.styleme.microOrquestrador.sagaCadastro;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaCadastro;
import com.styleme.microOrquestrador.repository.CadastroRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.styleme.microOrquestrador.repository.ChDoneRepository;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Saga1UserProducer {

    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private CadastroRepository rep;
    
    
    //Primeiro da sequencia
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueCadastro.queueCadastroUser, dto);
    }

    
    public void rollbackOrdem(MensagemDTO msg) {
        if(msg.getSagaId() != null){
            Optional<SagaCadastro> model = rep.findById(msg.getSagaId());
            if(model.isPresent()){
                
                msg.setSendObj(model.get().getId());
                template.convertAndSend(ConfigQueueCadastro.queueCadastroUserRollback, msg);
                //Faz alguma coisa
                rep.deleteById(msg.getSagaId());                
            }else{
                System.err.println("Id Não Existe - Rollback de Saga1CadastroUserProducer");
            }
        }else{
            System.err.println("Id não pode ser Null - Rollback de Saga1CadastroUserProducer");
        }
    }
    
}
