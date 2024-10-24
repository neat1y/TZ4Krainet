package org.example.tz4krainet.Security;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final HttpServletResponse httpServletResponse;
    private JWTUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader =request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token =authHeader.substring(7);
            if(token.isBlank()){
               httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid jwt token");
            }
            Map<String, Claim> claims=jwtUtils.validateTokenAndRetrieveClaim(token);
            List<String> role= List.of(claims.get("role").asString());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken
                    (claims.get("id"),null, role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
