package com.brown.springwebproject.config.auth.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomHeaderFilter extends OncePerRequestFilter
{
    private final JwtProvider jwtProvider;

    public CustomHeaderFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        String bearerToken = request.getHeader("Authorization");
//        SecurityContextHolder.getContext().setAuthentication();
        JWTClaimsSet claim = jwtProvider.getClaimFromToken(bearerToken);
        filterChain.doFilter(request, response);
    }

}