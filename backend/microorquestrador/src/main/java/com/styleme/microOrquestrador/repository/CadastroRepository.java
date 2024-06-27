
package com.styleme.microOrquestrador.repository;

import com.styleme.microOrquestrador.model.SagaCadastro;
import org.springframework.data.repository.CrudRepository;


public interface CadastroRepository extends CrudRepository<SagaCadastro, Long> {
    
}
