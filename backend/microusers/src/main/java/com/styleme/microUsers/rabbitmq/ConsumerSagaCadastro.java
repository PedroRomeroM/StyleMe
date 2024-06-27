
package com.styleme.microUsers.rabbitmq;

import com.styleme.microUsers.DTOs.AuthDTO;
import com.styleme.microUsers.DTOs.CadastroFullMsgDTO;
import com.styleme.microUsers.DTOs.MensagemDTO;
import com.styleme.microUsers.DTOs.UserTotalDTO;
import com.styleme.microUsers.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSagaCadastro {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private UsersService serv;

    @RabbitListener(queues = "cadastro-user")
    public void commitOrdem(@Payload MensagemDTO msg) {
        System.out.println("payload: " + msg.getSendObj());
        CadastroFullMsgDTO dto = mapper.map(msg.getSendObj(), CadastroFullMsgDTO.class);
        System.out.println("com.styleme.microUsers: " + dto.getImgcontent() + "\n" + dto.getImgtype());
        try {
            
           UserTotalDTO fnDto = serv.insertUser(dto.getUsername(), dto.getImgcontent(), dto.getImgtype());
           AuthDTO ret = new AuthDTO();
           
           ret.setId(fnDto.getIdUser());
           ret.setEmail(dto.getEmail());
           ret.setSenha(dto.getSenha());
           ret.setTipoUser("");
           
           msg.setReturnObj(ret);
           
        } catch (Exception ex) {
            msg.setMensagem(ex.getMessage());
            System.out.println("erro: " + ex.getMessage());
        }
        template.convertAndSend("cadastro-user-receive", msg);
    }

    @RabbitListener(queues = "cadastro-user-rollback")
    public void rollbackOrdem(@Payload MensagemDTO msg) {
        try {
            Integer id = mapper.map(msg.getSendObj(), Integer.class);
            serv.deleteUser(id);
        } catch (Exception ex) {
            System.out.println("Deu erro no Módulo Auth cadastro: " + ex.getMessage());
        }
    }
}
