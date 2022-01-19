package com.blue.api.config;

import com.blue.api.entities.User;
import com.blue.api.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserService userService, JWTTokenHelper jwtTokenHelper) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authToken = jwtTokenHelper.getToken(request);

        if(authToken != null) {
            String userEmail = jwtTokenHelper.getUserEmailFromToken(authToken);

            if(userEmail != null) {

                User userDetails = userService.loadUserByUsername(userEmail);
                boolean isValidToken = jwtTokenHelper.validateToken(authToken, userDetails);

                if(isValidToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }

        filterChain.doFilter(request, response);
    }
}
