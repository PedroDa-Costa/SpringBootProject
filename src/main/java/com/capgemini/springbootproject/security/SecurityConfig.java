package com.capgemini.springbootproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from members where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select m.username, a.authority from members m inner join authorities a on m.id = a.id where m.username = ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                config -> config.anyRequest().authenticated()
        )
            .formLogin(form -> form.loginPage("/loginForm").loginProcessingUrl("/authenticateTheUser").permitAll()
        )
            .logout(logout -> logout.permitAll().deleteCookies("JSESSIONID")
        );

        return http.build();
    }

}
