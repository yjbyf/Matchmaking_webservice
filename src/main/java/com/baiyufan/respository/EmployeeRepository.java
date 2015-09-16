
package com.baiyufan.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.baiyufan.utils.Constants;

@RepositoryRestResource(collectionResourceRel = Constants.EMPLOYEE_REST_WEBSERVICE_PATH, path = Constants.EMPLOYEE_REST_WEBSERVICE_PATH)
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	//List<User> findByUserName(@Param("name") String name);

}
