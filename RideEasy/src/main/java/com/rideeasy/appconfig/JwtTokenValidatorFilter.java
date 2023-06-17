package com.rideeasy.appconfig;

import com.rideeasy.exception.RideEasyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("inside Jwt token validator filter ");

        String jwt= request.getHeader(SecurityConstants.JWT_HEADER);

        if(jwt!=null){
            try {
                //extracting the word Bearer with space;
                jwt= jwt.substring(7);

                SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

                Authentication authentication= new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception ex){
                throw new RideEasyException("Invalid token ");
            }
        }
        else {
            throw new RideEasyException("No Authority Header found ");
        }

        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/signIn");
    }
}
