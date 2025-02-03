package com.xvlkk.sp.shops.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.application.security.jwt")
public class JwtConfig {
    private String secretKey;
    private String issuerApplication;
    private String expiration;

}
