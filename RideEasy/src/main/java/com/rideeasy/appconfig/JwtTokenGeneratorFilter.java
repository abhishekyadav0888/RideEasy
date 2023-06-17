package com.rideeasy.appconfig;

import com.rideeasy.exception.RideEasyException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     * Creates a Jwt token after authentication is done and add this token to the response.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("inside Jwt token generator filter ");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth !=null){

            SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

            String jwt= Jwts.builder()
                    .setIssuer("Lokesh")
                    .setSubject("Jwt Token")
                    .claim("username", auth.getName())
                    .claim("authorities", populatedAuthorities(auth.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 3600000))
                    .signWith(key).compact();

            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }else {
            throw new RideEasyException("User is not authenticated ");
        }

        filterChain.doFilter(request,response);
    }
    private String populatedAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authoritiesSet= new HashSet<>();
        for (GrantedAuthority authority: collection){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/signIn");
    }
}
