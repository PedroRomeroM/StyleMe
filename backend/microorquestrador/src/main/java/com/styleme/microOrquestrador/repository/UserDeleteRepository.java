
package com.styleme.microOrquestrador.repository;

import com.styleme.microOrquestrador.model.SagaDeleteUser;
import org.springframework.data.repository.CrudRepository;


public interface UserDeleteRepository extends CrudRepository<SagaDeleteUser, Long> {
    
}
