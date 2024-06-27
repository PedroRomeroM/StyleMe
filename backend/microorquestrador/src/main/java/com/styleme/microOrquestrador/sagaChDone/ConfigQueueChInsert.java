
package com.styleme.microOrquestrador.sagaChDone;

import com.styleme.microOrquestrador.sagaCadastro.*;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueueChInsert {
    
    //1° Passo
    public static String queueChDoneCh = "chdone-ch";
    
    public static String queueChDoneChReceive = "chdone-ch-receive";
    
    public static String queueChDoneChRollback = "chdone-ch-rollback";

    
    //2° Passo 
    public static String queueUserDoneCh = "chdone-user";
    
    public static String queueUserDoneChReceive = "chdone-user-receive";
        
    
    //1° Passo
    @Bean
    public Queue queueChDoneCh() {
        return new Queue(queueChDoneCh);
    }

    @Bean
    public Queue queueChDoneChReceive() {
        return new Queue(queueChDoneChReceive);
    }
 
    @Bean
    public Queue queueChDoneChRollback() {
        return new Queue(queueChDoneChRollback);
    }
    
    //2° Passo
    @Bean
    public Queue queueUserDoneCh() {
        return new Queue(queueUserDoneCh);
    }
    
    @Bean
    public Queue queueUserDoneChReceive() {
        return new Queue(queueUserDoneChReceive);
    }
    
}
