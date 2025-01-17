package com.toyotaproje.arizatakipsistemi.security;

import com.toyotaproje.arizatakipsistemi.dao.PersonelRepository;
import com.toyotaproje.arizatakipsistemi.model.Personel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.EXPIRATION_TIME;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.HEADER_STRING;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.SECRET;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.TOKEN_PREFIX;
import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.*;

//Bu sınıfımızın iki görevi var. Başarılı login’lere JWT atama ve yeni authorized requestlerin JWT’sini kontrol etme.
public class JWTAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PersonelRepository personalRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //İlk attemptAuthentication fonsiyonunda gelen request’teki Header’i parse edip Token’i alıyoruz. Ardından 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	Personel creds = new ObjectMapper()	
                    .readValue(req.getInputStream(), Personel.class);
        	
        	//UsernamePasswordAuthenticationToken sınıfının bir nesnesini oluşturup JWT’mizi set ediyoruz.
        	//Nesnesini oluşturduğumuz bu sınıf kullanıcı adı ve parolayı saklar. getCredentials() ve getPrinciples() methodları ile bunlara erişebiliriz.
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //succesfulAuthentication ile başarılı bir şekilde login olmuş kullanıcıya JWT yollayacağız
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	// Bunun için Jwts sınıfının builder() methodu ile kendi tanımladığımız SECRET ile imzalayıp Tokenimizi oluşturuyoruz ve Header’a ekliyoruz.
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //token'ın geçerlilik süresi
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())  //Token’in HMAC algoritması ile imzalanırken kulladığı secret key, istediğimiz değeri verebiliriz.
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(
                "{\"" + SecurityConstants.HEADER_STRING + "\":\"" + SecurityConstants.TOKEN_PREFIX+token + "\"}" //Authorization Header’ındaki auth tipi.(basic, bearer, ..)
        );
    }
}