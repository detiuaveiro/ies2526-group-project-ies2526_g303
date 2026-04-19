package smarthome.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Obrigatório para o Postman conseguir fazer POST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()        // Liberta a tua API
                .requestMatchers("/swagger-ui/**").permitAll() // Liberta o Swagger
                .requestMatchers("/v3/api-docs/**").permitAll() 
                .anyRequest().authenticated()                  // O resto fica trancado
            );
        return http.build();
    }
}

// Mais tarde mudar o .permitAll() para .authenticated()