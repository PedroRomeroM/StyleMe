
package com.styleme.microOrquestrador.sagaCadastro;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueueCadastro {
    
    //1° Passo
    public static String queueCadastroUser = "cadastro-user";
    
    public static String queueCadastroUserReceive = "cadastro-user-receive";
    
    public static String queueCadastroUserRollback = "cadastro-user-rollback";

    
    //2° Passo 
    public static String queueCadastroAuth = "cadastro-auth";
    
    public static String queueCadastroAuthReceive = "cadastro-auth-receive";
        
    
    //1° Passo
    @Bean
    public Queue queueCadastroUser() {
        return new Queue(queueCadastroUser);
    }

    @Bean
    public Queue queueCadastroUserReceive() {
        return new Queue(queueCadastroUserReceive);
    }
 
    @Bean
    public Queue queueCadastroUserRollback() {
        return new Queue(queueCadastroUserRollback);
    }
    
    //2° Passo
    @Bean
    public Queue queueCadastroAuth() {
        return new Queue(queueCadastroAuth);
    }
    
    @Bean
    public Queue queueCadastroAuthReceive() {
        return new Queue(queueCadastroAuthReceive);
    }
        
    
}
