package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	@Bean
	public UserDetailsService userDetailsService() {

		return super.userDetailsService();

	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

	}
	
	  @Override 
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		  auth.userDetailsService(userDetailsService);
	  }
	 
    @Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/api/getinfo","/api/jobs/{j_id}","/authenticate","/token","/forgot-pass","/api/candidates","/api/candidates/{c_id}","/api/login","/api/jobs","/api/jobs/{pageNo}/{pageSize}","/api/jobs/apply/{pageNo}/{pageSize}","/api/changePass/{id}","/api/forgot-pass-confirm","/api/logout","/forgot-pass","/mail","/api/sendmail").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //.and().exceptionHandling().accessDeniedPage("/err/403");
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
	
	
	
	
	/*
	 * @Autowired private MailService mailService;
	 */

	
}
