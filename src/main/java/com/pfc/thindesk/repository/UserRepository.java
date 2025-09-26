package com.pfc.thindesk.repository;

import com.pfc.thindesk.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // O Spring Data cria automaticamente a consulta para buscar um usuário pelo username
    User findByUsername(String username);
}
