package com.springboot.case_study_evaluation.config;

import com.springboot.case_study_evaluation.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService ;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/api/employee/add").hasAuthority("ADMIN")
                        // login API
                        .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()

                        .requestMatchers(HttpMethod.GET,"/api/tasks/getAllTasks").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/tasks/{taskId}").hasAuthority("EMPLOYEE")

                        .requestMatchers(HttpMethod.PUT,"/api/tasks/update/{taskId}/{taskStatus}").hasAuthority("EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE,"/api/tasks/delete/{taskId}").hasAuthority("ADMIN")

                        // Admin onboard API (permitAll to add) (After addition - denyAll)
                        .requestMatchers(HttpMethod.POST,"/api/admin/add").permitAll()
                        .anyRequest().permitAll()
                );

        // Add jwt filter before Spring's Auth Filter
        http.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public AuthenticationProvider getProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(getEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
