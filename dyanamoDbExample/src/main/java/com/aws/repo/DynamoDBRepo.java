package com.aws.repo;

import com.aws.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface DynamoDBRepo extends CrudRepository<User, String> {
}
