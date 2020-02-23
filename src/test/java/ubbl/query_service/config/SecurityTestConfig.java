package ubbl.query_service.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import ubbl.query_service.model.User;

@TestConfiguration
public class SecurityTestConfig {
    @Bean
    @Primary
    public UserDetailsService testUserDetailsService() {
        User user = new User("testUser", "password", "Test", "User");
        
        return new InMemoryUserDetailsManager(user);
    }
}