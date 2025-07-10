package com.infosys.employee.repository;

import com.infosys.employee.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}