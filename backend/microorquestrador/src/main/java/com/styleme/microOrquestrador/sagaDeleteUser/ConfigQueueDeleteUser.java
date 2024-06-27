
package com.styleme.microOrquestrador.sagaDeleteUser;

import com.styleme.microOrquestrador.sagaDeleteCh.*;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueueDeleteUser {
    
    //1° Passo
    public static String queueUserDeleteCh = "userdelete-ch";
    
    public static String queueUserDeleteUs = "userdelete-us";
    
    public static String queueUserDeleteReceive = "userdelete-receive";
    
    
    //2° Passo
    public static String queueUserDeleteCh2 = "userdelete-ch-2";
    
    public static String queueUserDeleteUs2 = "userdelete-us-2";
    
    
    
    //1° Passo
    @Bean
    public Queue queueUserDeleteCh() {
        return new Queue(queueUserDeleteCh);
    }

    @Bean
    public Queue queueUserDeleteUs() {
        return new Queue(queueUserDeleteUs);
    }
 
    @Bean
    public Queue queueUserDeleteReceive() {
        return new Queue(queueUserDeleteReceive);
    }
    
    //2° Passo
    @Bean
    public Queue queueUserDeleteCh2() {
        return new Queue(queueUserDeleteCh2);
    }
    
    @Bean
    public Queue queueUserDeleteUs2() {
        return new Queue(queueUserDeleteUs2);
    }
    
}
