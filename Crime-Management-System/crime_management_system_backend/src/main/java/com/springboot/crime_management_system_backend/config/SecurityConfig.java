package com.springboot.crime_management_system_backend.config;

import com.springboot.crime_management_system_backend.service.MyUserDetailsService;
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
                        .requestMatchers(HttpMethod.POST,"/api/civilian/add").permitAll() //done
                        // login API
                        .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()  //done

                        .requestMatchers(HttpMethod.POST,"/api/civilian/post-incident/{civilianId}").hasAuthority("CIVILIAN") //done
                        .requestMatchers(HttpMethod.POST,"/api/officer/add").hasAuthority("STATIONHEAD") //done
                        .requestMatchers(HttpMethod.POST,"/api/stationHead/add").hasAuthority("STATIONHEAD") //done
                        .requestMatchers(HttpMethod.GET,"/api/case-record/unassigned").hasAuthority("STATIONHEAD")  //done
                        .requestMatchers(HttpMethod.PUT,"/api/case-record/update/{officerId}/{caseId}").hasAuthority("STATIONHEAD") //done
                        .requestMatchers(HttpMethod.GET,"/api/civilian/getAllCases").hasAuthority("CIVILIAN")  //done
                        .requestMatchers(HttpMethod.GET,"/api/civilian/getCaseStatus/{caseId}").hasAuthority("CIVILIAN")  //done
                        .requestMatchers(HttpMethod.GET,"/api/officer/viewAssignedCases").hasAuthority("OFFICER")  //done
                        .requestMatchers(HttpMethod.PUT,"/api/case-record/updateStatus/{caseId}").hasAuthority("OFFICER") //done
                        .requestMatchers(HttpMethod.GET,"/api/stationHead/viewDetails/{officerId}").hasAuthority("STATIONHEAD")  //done
                        .requestMatchers(HttpMethod.GET,"/api/stationHead/viewAllOfficer").hasAuthority("STATIONHEAD")  //done
                        .requestMatchers(HttpMethod.PUT,"/api/case-record/verifyStatus/{caseId}").hasAuthority("STATIONHEAD") //done
                        .requestMatchers(HttpMethod.GET,"/api/stationHead/viewCaseStatus").hasAuthority("STATIONHEAD")  //done

                        // Admin onboard API (permitAll to add) (After addition - denyAll)
                        .requestMatchers(HttpMethod.POST,"/api/admin/add").denyAll() //done
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
