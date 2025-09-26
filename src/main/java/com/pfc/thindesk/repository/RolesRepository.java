package com.pfc.thindesk.repository;

import com.pfc.thindesk.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolesRepository extends MongoRepository<Role, String> {
    //Role findByUsername(String name);
}
