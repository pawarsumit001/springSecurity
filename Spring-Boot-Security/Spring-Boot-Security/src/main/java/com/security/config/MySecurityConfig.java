package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
/*
this is use to get double access
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               .and()
               .authorizeRequests()
               .antMatchers("/signin").permitAll()
              /* .antMatchers("/home","/login","/register").permitAll()*/
               .antMatchers("/public/**").hasRole("Normal")
             //public se start hone wale jo bhi controller hoge usemien security nhi hogi
               .antMatchers("*/users/**").hasRole("ADMIN")
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               //using html signin page
               .loginPage("/signin")
               .loginProcessingUrl("/dologin")
               .defaultSuccessUrl("/users/");
             /* is used for basic auth .httpBasic();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.
                inMemoryAuthentication()
                .withUser("user")
                .password(this.passwordEncoder().encode("user"))
                .roles("Normal");

        auth.
                inMemoryAuthentication()
                .withUser("admin")
                .password(this.passwordEncoder().encode("admin"))
                .roles("ADMIN");



    }
    //role =high level  overview ===>> NOrmal ,READ;
    //Authority ===>> persmission  ===>> READ
    //ADMIN ===>> READ,WRITE,UPDATE

    @Bean
    public PasswordEncoder passwordEncoder(){

     /* // without encoded password
       return NoOpPasswordEncoder.getInstance();*/
    //encoded password
        return new BCryptPasswordEncoder(10);
    }
}
