package com.naval_innovators.your_exam_sathi.auth_service.config;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.LoginRequest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;

//	custom login end point added.
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
        this.setFilterProcessesUrl("/api/auth/login");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Deserialize the login request
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            // Determine whether to use username or email for authentication
            String identifier = loginRequest.getUserName() != null ? loginRequest.getUserName() : loginRequest.getEmail();

            // Create the authentication token
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(identifier, loginRequest.getPassword());

            // Authenticate using the token
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        String username = authResult.getName();
//        String token = jwtUtil.generateToken(username);       
//        response.setHeader("Authorization", "Bearer " + token);
//        response.setContentType("application/json");
//        response.getWriter().write("{\"token\": \"" + token + "\"}");
//        response.getWriter().flush();
//        response.getWriter().write(new ObjectMapper().writeValueAsString("Login successful"));
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
    		AuthenticationException failed) throws IOException, ServletException {
    	if (failed.getMessage().equalsIgnoreCase("Bad credentials")) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("User not found");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed");
        }
    }
    

}
