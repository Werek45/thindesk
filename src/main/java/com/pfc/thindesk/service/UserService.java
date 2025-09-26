package com.pfc.thindesk.service;

import com.pfc.thindesk.dto.UserDto;

// Este é o contrato. Ele apenas diz quais métodos nosso serviço de usuário terá.
public interface UserService {
    void saveUser(UserDto userDto);
}
