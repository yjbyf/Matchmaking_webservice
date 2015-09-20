
package com.baiyufan.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

@RepositoryRestResource(collectionResourceRel = Constants.MATCH_REST_WEBSERVICE_PATH, path = Constants.MATCH_REST_WEBSERVICE_PATH)
public interface MatchRepository extends MongoRepository<Match, String> {
	//List<User> findByUserName(@Param("name") String name);

}
