package com.xvlkk.sp.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.application.security")
public class SecurityCorsConfig implements WebMvcConfigurer {
    private String requestMatchers;

    private String originAllowed;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(getCorsList())
                .allowedHeaders("x-requested-with", "authorization", "Content-Type", "Authorization", "credential", "X-XSRF-TOKEN", "AuthorizationRefreshToken")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",  "HEAD", "TRACE", "CONNECT", "PATCH")
                .allowCredentials(true);
    }

    public String[] getCorsList(){
        return originAllowed.split(",");
    }

    public String[] getRequestMatchers() {
        return requestMatchers.split(",");
    }
}
