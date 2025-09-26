package com.pfc.thindesk.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Data
@Document(collection = "users") // Mapeia para a coleção "users"
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    @DBRef // Cria uma referência para documentos em outra coleção (a de Roles)
    private Set<Role> roles;
}
