package com.toyotaproje.arizatakipsistemi.security;

import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.HEADER_STRING;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.SECRET;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.toyotaproje.arizatakipsistemi.dao.PersonelRepository;

import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
// İlk methodumuzda kalıtım aldığımız soyut BasicAuthenticationFilter sınıfının doFilter() methodunu implemente ediyoruz.
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
//JWT’yi validate eder ve herşey uygunsa kullanıcı authentication bilgilerini SecurityContextHolder ile SecurityContext’in içine yazar ve kaynağı client’a sunar.
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
//İkincisi JWTAuthenticationFilter sınıfındaki successfulAuthentication() methoduyla benzer, Authorization Header’ından JWT değerini okur ve token’in geçerliliğini onaylar.
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}