package com.udacity.locationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Slf4j
public class LocationServiceWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {
   @Autowired
   private DataSource dataSource;

   @Override
   @Autowired
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource);
   }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
      log.info("Configuring Spring security");
      httpSecurity.csrf().disable();
      httpSecurity.httpBasic();
      httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      httpSecurity.authorizeRequests().anyRequest().authenticated();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/v2/api-docs",
              "/configuration/ui",
              "/swagger-resources",
              "/configuration/security",
              "/swagger-ui.html",
              "/webjars/**",
              "/h2-console/**");
   }
}
