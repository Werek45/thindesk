package com.pfc.thindesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean para criptografar senhas. Sempre que precisarmos de um PasswordEncoder,
    // o Spring nos dará esta instância do BCrypt.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura a cadeia de filtros de segurança
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http
            // Autorização de requisições
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                // Permite acesso público a estas URLs (registro, css, js)
                .requestMatchers("/login", "/css/**", "/dist/**", "/plugins/**", "/js/**", "/images/**").permitAll() // Permitir acesso à página de login e arquivos estáticos
                .anyRequest().authenticated() // Exigir autenticação para qualquer outra página
            )
            .formLogin(form -> form
            // URL da página de login personalizada
                .loginPage("/login") 
                // URL que o formulário de login vai submeter (Spring Security cuida disso)
                .loginProcessingUrl("/login")
                // Definir a URL da página de login personalizada
                .defaultSuccessUrl("/dashboard", true) // Redirecionar para /home após login bem-sucedido
                .permitAll() // Permitir que todos acessem a página de login
            )
            //logout
            .logout(logout -> logout
                // URL que aciona o logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // Permite que qualquer um acesse a URL de logout
                .permitAll()
            );
        return http.build();
    }
}
