package com.toyotaproje.arizatakipsistemi.security;

import static com.toyotaproje.arizatakipsistemi.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.toyotaproje.arizatakipsistemi.security.JWTAuthenticationFilter;
import com.toyotaproje.arizatakipsistemi.security.JWTAuthorizationFilter;

//İki sınıfıda Spring Security'e implemente ediyoruz.
//Bu sinifla hangi kaynaklara izin verip vermedigimizi ve hangi filtreleri kullanacagimizi yazacagiz.
//Genel guvenlik unsurlarinin hepsi burada yer alir.
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private UserDetailsService userDetailsService;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userDetailsService = userDetailsService;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
//burada hangi methodlara ve path’lere izin verdigimizi belirtiyoruz
//Sonrasinda /sign-up path’ine POST request’e izin verip, geri kalan tum kaynaklar icin authentication bekledigimizi soyluyoruz.
//Ardindan az once yarattigimiz iki Filter’i ekleyip, session olusturmayi kaldiriyoruz. Buni JWT ile saglayacagiz.
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable().authorizeRequests()
              .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
              .anyRequest().authenticated()
              .and()
              .addFilter(new JWTAuthenticationFilter(authenticationManager()))
              .addFilter(new JWTAuthorizationFilter(authenticationManager()))
              // this disables session creation on Spring Security
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
//auth.userDetailsService(T) methodu, Spring Docs’ta arguman olarak verilen userDetailsService’e yani user-specific bilgileri authenticate ediyor
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }
//Burada Cross Origin Resource Sharing(CORS) izinlerini set ediyoruz, burada herhangi kaynaktan gelen requestlere izin verdik.
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
  }

}