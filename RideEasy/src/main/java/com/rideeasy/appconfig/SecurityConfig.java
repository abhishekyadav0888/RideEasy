package com.rideeasy.appconfig;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception{

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .cors(cors -> {

            cors.configurationSource(new CorsConfigurationSource() {

                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration cfg = new CorsConfiguration();

                    cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                    cfg.setAllowedMethods(Collections.singletonList("*"));
                    cfg.setAllowCredentials(true);
                    cfg.setAllowedHeaders(Collections.singletonList("*"));
                    cfg.setExposedHeaders(Arrays.asList("Authorization"));
                    return cfg;
                }
            });

        })
                .authorizeHttpRequests(auth->{
                auth.requestMatchers(HttpMethod.POST,"/drivers/add","/customers/customer","/admin/add").permitAll()


                        .requestMatchers(HttpMethod.GET,"/customers/**","/trip-bookings/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**","/drivers/**","/cabs/**").hasRole("ADMIN")

//
                        .requestMatchers(HttpMethod.PUT,"/customers/customer").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/customers/customer/{customerId}").hasRole("CUSTOMER")
                        .requestMatchers("/trip-bookings/**","/trip-bookings","/customers/customer/hello").hasRole("CUSTOMER")

//                        .requestMatchers("/customer/**").hasRole("CUSTOMER")

                        .requestMatchers("/drivers/add","/drivers/update","drivers/delete").hasRole("DRIVER")
                        .requestMatchers("/drivers/update","/drivers/delete/{id}","/drivers/hello").hasRole("DRIVER")
                .anyRequest().authenticated();
        })
        .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)

//        .csrf(csrf-> csrf.ignoringRequestMatchers("/drivers/add", "/customers","/admin"))
        .csrf(csrf -> csrf.disable())


        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
