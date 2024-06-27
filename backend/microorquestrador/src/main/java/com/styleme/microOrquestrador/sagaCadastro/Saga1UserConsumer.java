package com.styleme.microOrquestrador.sagaCadastro;

import com.styleme.microOrquestrador.DTO.AuthDTO;
import com.styleme.microOrquestrador.DTO.MensagemDTO;
import com.styleme.microOrquestrador.model.SagaCadastro;
import com.styleme.microOrquestrador.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import com.styleme.microOrquestrador.repository.ChDoneRepository;


@Component
public class Saga1UserConsumer {

    @Autowired
    private Saga2AuthProducer next;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private CadastroRepository rep;   
    
    @RabbitListener(queues = "cadastro-user-receive")
    public void receiveMsg(@Payload MensagemDTO msg) {
        if(msg.getMensagem() == null){
            //Recebe GerenteDTO pelo returnObj -> Converte pro formato de inserir no bd direto
            AuthDTO dto = mapper.map(msg.getReturnObj(), AuthDTO.class);
            
            SagaCadastro model = new SagaCadastro();
            model.setId(dto.getId());
            
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
