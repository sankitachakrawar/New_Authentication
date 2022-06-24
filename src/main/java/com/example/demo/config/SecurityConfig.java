package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	/*
	 * @Autowired private MailService mailService;
	 */
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        		http
	                .csrf().disable()
	                .authorizeHttpRequests()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	              
	       // .antMatcher("/api/auth/**")
	       .logout();
	       
	       
	               
	    }
	 @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}
