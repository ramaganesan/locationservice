package com.udacity.locationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
      httpSecurity.authorizeRequests()
              .antMatchers("/h2-console/**", "/graphql/**")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .httpBasic();
      httpSecurity.csrf()
              .ignoringAntMatchers("/h2-console/**");
      httpSecurity.headers()
              .frameOptions()
              .sameOrigin();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
