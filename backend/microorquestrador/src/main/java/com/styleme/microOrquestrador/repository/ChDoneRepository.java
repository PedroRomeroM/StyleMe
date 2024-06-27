
package com.styleme.microOrquestrador.repository;

import com.styleme.microOrquestrador.model.SagaChDone;
import org.springframework.data.repository.CrudRepository;


public interface ChDoneRepository extends CrudRepository<SagaChDone, Long> {
    
}
