package com.xvlkk.sp.shops.security.config;

import com.xvlkk.sp.shops.security.filter.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final SecurityCorsConfig securityCorsConfig;

    private final UserAuthenticationFilter userAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(SecurityCorsConfig securityCorsConfig, UserAuthenticationFilter userAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.securityCorsConfig = securityCorsConfig;
        this.userAuthenticationFilter = userAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    @Primary
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(securityCorsConfig.getRequestMatchers()).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(
                        userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public String[] getRequestMarchersList(){
        return securityCorsConfig.getRequestMatchers();
    }

}
