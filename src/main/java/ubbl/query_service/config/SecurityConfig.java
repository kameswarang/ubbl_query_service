package ubbl.query_service.config;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService uds;
    
    @Bean
    public PasswordEncoder passEnc() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder authMB) throws Exception {
        authMB
            .userDetailsService(uds)
            .passwordEncoder(passEnc());
    }
    
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
	    httpSec
	        .authorizeRequests()
	            .antMatchers("/home").hasRole("USER")
            .and()
                .formLogin().loginPage("/login")
            .and()
                .logout().logoutSuccessUrl("/login?logout");
	}
}