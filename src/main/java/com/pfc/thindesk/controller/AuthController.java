package com.pfc.thindesk.controller;

// A classe correta que criamos para o formulário
import com.pfc.thindesk.dto.UserDto; 
// O serviço correto que deve ser usado para salvar usuários
import com.pfc.thindesk.service.UserService; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

public AuthController(UserService userService) {
    this.userService = userService;
}

    // Exibe a página de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Retorna o nome do arquivo HTML: "login.html"
    }

    // Exibe o formulário de registro
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Adiciona um objeto UserDto vazio ao modelo para o formulário preencher
        model.addAttribute("user", new UserDto());
        return "register"; // Retorna "register.html"
    }

    // Processa os dados do formulário de registro
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/register?success"; // Redireciona de volta com um parâmetro de sucesso
    }

    // Exibe a página principal após o login
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Retorna "dashboard.html"
    }
}
