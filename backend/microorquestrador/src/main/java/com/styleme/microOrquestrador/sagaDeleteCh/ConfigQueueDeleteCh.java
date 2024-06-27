
package com.styleme.microOrquestrador.sagaDeleteCh;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueueDeleteCh {
    
    //1° Passo
    public static String queueChDeleteCh = "chdelete-ch";
    
    public static String queueChDeleteUs = "chdelete-us";
    
    public static String queueChDeleteReceive = "chdelete-receive";
    
    
    //2° Passo
    public static String queueChDeleteCh2 = "chdelete-ch-2";
    
    public static String queueChDeleteUs2 = "chdelete-us-2";
    
    
    
    //1° Passo
    @Bean
    public Queue queueChDeleteCh() {
        return new Queue(queueChDeleteCh);
    }

    @Bean
    public Queue queueChDeleteUs() {
        return new Queue(queueChDeleteUs);
    }
 
    @Bean
    public Queue queueChDeleteReceive() {
        return new Queue(queueChDeleteReceive);
    }
    
    //2° Passo
    @Bean
    public Queue queueChDeleteCh2() {
        return new Queue(queueChDeleteCh2);
    }
    
    @Bean
    public Queue queueChDeleteUs2() {
        return new Queue(queueChDeleteUs2);
    }
    
}
