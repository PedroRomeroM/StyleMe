/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.styleme.microOrquestrador.sagaCadastro;

import com.styleme.microOrquestrador.DTO.CadastroFullDTO;
import com.styleme.microOrquestrador.DTO.CadastroFullMsgDTO;
import com.styleme.microOrquestrador.DTO.MensagemDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitSagaCadastro {
    
    @Autowired
    private Saga1UserProducer prod;
    
    
    public void cadastro(CadastroFullMsgDTO dto){
        MensagemDTO msg = new MensagemDTO();
        
        
        msg.setSendObj(dto);
        
        prod.commitOrdem(msg);
    }
    
}
