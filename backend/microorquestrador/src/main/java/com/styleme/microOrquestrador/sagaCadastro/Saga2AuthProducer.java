
package com.styleme.microOrquestrador.sagaCadastro;

import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaCadastro;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.styleme.microOrquestrador.repository.ChDoneRepository;

@Component
public class Saga2AuthProducer {

    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ChDoneRepository rep;
    
    
    //Primeiro da sequencia
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigQueueCadastro.queueCadastroAuth, dto);
    }
    
    
}
