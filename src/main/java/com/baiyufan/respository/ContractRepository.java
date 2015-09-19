
package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

@RepositoryRestResource(collectionResourceRel = Constants.CONTRACT_REST_WEBSERVICE_PATH, path = Constants.CONTRACT_REST_WEBSERVICE_PATH)
public interface ContractRepository extends MongoRepository<Contract, String> {

	//List<User> findByUserName(@Param("name") String name);
	//List<User> findByPersonId(@Param("personId") String personId);

}
