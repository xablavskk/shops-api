package com.xvlkk.sp.security.filter;

import com.xvlkk.sp.security.config.SecurityCorsConfig;
import com.xvlkk.sp.security.dto.UserDetailsImpl;
import com.xvlkk.sp.security.repository.UserRepository;
import com.xvlkk.sp.security.service.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final SecurityCorsConfig securityCorsConfig;
    private final UserRepository userRepository;

    private static final String BEARER = "Bearer ";

    /** implementation is provided in config.ApplicationSecurityConfig */

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwt = getJwt(request);
        final String authHeader = request.getHeader("Authorization");

        if((jwt == null
                && (authHeader ==  null || !authHeader.startsWith(BEARER)))
                || !isValidaBearer(request)){

            filterChain.doFilter(request, response);
            return;
        }

        if (jwt == null && authHeader.startsWith(BEARER)) {
            jwt = authHeader.substring(7);
        }

        String userEmail;

        try {
            userEmail = jwtTokenService.extractUsername(jwt);
        } catch (ExpiredJwtException ex) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return;
        }

        /*
           SecurityContextHolder: is where Spring Security stores the details of who is authenticated.
           Spring Security uses that information for authorization.*/

        if(StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetailsImpl userDetails = this.userRepository.userDetailsDTOToUser(userEmail);

            if(jwtTokenService.isTokenValid(jwt, userDetails)){

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request,response);
    }

    private boolean isValidaBearer(HttpServletRequest request){
        return Arrays.stream(securityCorsConfig.getRequestMatchers())
                .noneMatch(f -> request.getRequestURI().contains(f));
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(BEARER)) {
            return authHeader.replace(BEARER, "");
        }

        return null;
    }
}
