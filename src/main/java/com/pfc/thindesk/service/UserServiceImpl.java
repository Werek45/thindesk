package com.pfc.thindesk.service;

import com.pfc.thindesk.dto.UserDto;
import com.pfc.thindesk.model.Role;
import com.pfc.thindesk.model.User;
import com.pfc.thindesk.repository.RoleRepository;
import com.pfc.thindesk.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // O Spring vai injetar automaticamente os repositórios e o codificador de senha
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());

        // Criptografa a senha antes de salvar! Este é um passo de segurança crucial.
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Atribui um perfil padrão de "ROLE_USER" para novos usuários
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            // Se o perfil não existir no banco, ele é criado
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        
        userRepository.save(user);
    }
}
