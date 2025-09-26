package com.pfc.thindesk.repository;

import com.pfc.thindesk.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    // Este método permite buscar um perfil pelo nome, ex: "ROLE_USER"
    Role findByName(String name);
}
