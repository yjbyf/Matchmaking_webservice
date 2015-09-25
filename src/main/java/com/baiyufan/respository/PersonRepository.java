
package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

//@RepositoryRestResource(collectionResourceRel = Constants.PERSON_REST_WEBSERVICE_PATH, path = Constants.PERSON_REST_WEBSERVICE_PATH)
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByName(@Param("name") String name);

}
