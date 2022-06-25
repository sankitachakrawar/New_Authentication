package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.JwtFilter;
import com.example.demo.services.UserService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/token","/forgot-pass","/api/candidates","/api/candidates/{c_id}","/api/login","/api/jobs").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic().and()      
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //.and().exceptionHandling().accessDeniedPage("/err/403");
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
	
	
	
	
	/*
	 * @Autowired private MailService mailService;
	 */

	/*
	 * @Autowired private CandidateDetailsService candidateDetailsService;
	 */
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf() .disable() .cors() .disable() .authorizeRequests()
	 * .antMatchers("/token").permitAll() .anyRequest().authenticated() .and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth)throws
	 * Exception { auth.userDetailsService(candidateDetailsService); }
	 * 
	 * @Override
	 * 
	 * @Bean public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 */
}
