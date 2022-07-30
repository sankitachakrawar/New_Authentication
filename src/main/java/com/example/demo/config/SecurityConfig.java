package com.example.demo.config;

import java.util.Arrays;



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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
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
		  http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/auth/login", "/auth/forgot-pass", "/api/register", "/api/forgot-pass-confirm").permitAll().
			anyRequest().authenticated().and().httpBasic().and().
			exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	  
	  }
  
		@Bean
		public CorsConfigurationSource corsConfigurationSource() {

			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("*"));
			configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
			configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
			configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;

		}
}






//http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS,
//"/**").permitAll().antMatchers("/auth/login","/api/resgister","/api/candidates/{id}","/auth/logout","/api/jobs/sort").permitAll().anyRequest()
//.authenticated().and().httpBasic().and().sessionManagement()
//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//.and().exceptionHandling().accessDeniedPage("/err/403");
//http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
