package com.styleme.microUsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.styleme.microUsers.DTOs.UserTotalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroUsersApplication {

        public static UserTotalDTO adminDto;
    
        @Bean
        public static ModelMapper modelMapper(){
            return new ModelMapper();
        }
        
        @Bean
        public MessageConverter jsonMessageConverter() {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.registerModule(new JavaTimeModule());
            return new Jackson2JsonMessageConverter(mapper);
        }
        
	public static void main(String[] args) {
		SpringApplication.run(MicroUsersApplication.class, args);
	}

}
