package com.requestdesign.testingservice.repository.auth;

import com.requestdesign.testingservice.entity.auth.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
