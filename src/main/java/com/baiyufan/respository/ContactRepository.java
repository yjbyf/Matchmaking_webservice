
package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

@RepositoryRestResource(collectionResourceRel = Constants.CONTACT_REST_WEBSERVICE_PATH, path = Constants.CONTACT_REST_WEBSERVICE_PATH)
public interface ContactRepository extends MongoRepository<Contact, String> {

	//List<User> findByUserName(@Param("name") String name);

}
