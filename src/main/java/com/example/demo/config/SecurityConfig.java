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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.demo.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtRequestFilter filter;
	
	  @Override
	  
	  @Bean public UserDetailsService userDetailsService() {
	  
	  return super.userDetailsService();
	  
	  }
	 
	
	  @Override 
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		  auth.userDetailsService(customUserDetailsService);
	  }
	 
		/*
		 * @Bean public PasswordEncoder passwordEncoder() {
		 * 
		 * return new BCryptPasswordEncoder();
		 * 
		 * }
		 */
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		  return NoOpPasswordEncoder.getInstance();
	  }
	  
	
	  
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  http.csrf() .disable() .authorizeRequests() .antMatchers(HttpMethod.OPTIONS,
	  "/**").permitAll().antMatchers("/api/authenticate","/api/assignJob","/api",
	  "/api/data","/api/getinfo","/api/jobs/{id}","/token","/forgot-pass",
	  "/api/candidates","/api/candidates/{c_id}","/api/login","/api/jobs",
	  "/api/jobs/{pageNo}/{pageSize}","/api/jobs/apply/{pageNo}/{pageSize}",
	  "/api/changePass/{c_id}","/api/forgot-pass-confirm","/api/logout",
	  "/forgot-pass","/mail","/api/sendmail","/api/jobs/applied","/api/jobs/apply").permitAll() .anyRequest()
	  .authenticated() .and().httpBasic().and() .sessionManagement()
	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  //.and().exceptionHandling().accessDeniedPage("/err/403");
	  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	  
	  }
	 
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf() .disable() .authorizeRequests()
	 * .antMatchers("/api/authenticate").permitAll().anyRequest().authenticated(); }
	 */
	/*
	 * @Autowired private MailService mailService;
	 */

	
}
