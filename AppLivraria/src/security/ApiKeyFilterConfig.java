package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class ApiKeyFilterConfig {

    // Registra o filtro de autenticação na aplicação
    @Bean
    public Filter apiKeyFilter() {
        return new ApiKeyFilter();
    }
}