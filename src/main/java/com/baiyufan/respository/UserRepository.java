
package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

@RepositoryRestResource(collectionResourceRel = Constants.USER_REST_WEBSERVICE_PATH, path = Constants.USER_REST_WEBSERVICE_PATH)
public interface UserRepository extends MongoRepository<User, String> {

	List<User> findByUserName(@Param("name") String name);
	List<User> findById(@Param("id") String id);
	List<User> findByAliveFlag(@Param("aliveFlag") String aliveFlag);

}
