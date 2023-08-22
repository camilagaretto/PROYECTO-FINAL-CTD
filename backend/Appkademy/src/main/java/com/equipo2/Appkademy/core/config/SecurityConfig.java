package com.equipo2.Appkademy.core.config;

import com.equipo2.Appkademy.core.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true) //Esto permite que el @PreAuthorize en el controller se tenga en cuenta (sino se lo ignora)
public class SecurityConfig {

    //TODO: REEMPLAZAR METODOS DEPRECADOS

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .cors().and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/auth/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/categories/1/providers/{id}")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/categories/1/providers/search")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/categories/1/customers/")
                .permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/documentation/**")
                .permitAll()
                .requestMatchers("/error")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
