package com.pfc.thindesk.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  // Lombok para gerar getters, setters, etc.
@Document(collection = "roles")  // Mapeia esta classe para a coleção "roles" no MongoDB

public class Role {
    @Id //Marca o campo como chave primaria
    private String id;
    private String name;  // Ex: "ROLE_USER", "ROLE_ADMIN"
}
