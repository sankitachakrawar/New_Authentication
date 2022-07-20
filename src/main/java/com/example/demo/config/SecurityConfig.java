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
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	
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
	 
		
		  @Bean public PasswordEncoder passwordEncoder() {
		  
		  return new BCryptPasswordEncoder();
		  
		  }
		  
		 
		 
	  
			/*
			 * @Bean public PasswordEncoder passwordEncoder() { return
			 * NoOpPasswordEncoder.getInstance(); }
			 */
	
	  
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
	  http.csrf() .disable() .authorizeRequests() .antMatchers(HttpMethod.OPTIONS,
	  "/**").permitAll().antMatchers("/api/authenticate","/api","/login","/logout",
	  "/api/data","/api/getinfo","/api/jobs/{id}","/token","/forgot-pass",
	  "/api/candidates","/api/candidates/{c_id}","/api/login","/api/jobs",
	  "/api/jobs/{pageNo}/{pageSize}","/api/jobs/apply/{pageNo}/{pageSize}","/api/forgot-pass-confirm",
	  "/mail","/api/sendmail","/api/jobs/applied","/api/jobs","/api/jobs/sort","/recruiter","/api/assignJob","/role","/api/permission","/api/permission/{id}","/permission/{id}","/api/entity",
	  "/api/entity/{id}","/api/role","/api/role/{id}","/api/permission_role/{id}").permitAll() .anyRequest()
	  .authenticated() .and().httpBasic().and() .sessionManagement()
	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	  .and().exceptionHandling().accessDeniedPage("/err/403");
	  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	  
	  }
//	  
//	  @Override
//		protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//			// We don't need CSRF for this example
//			httpSecurity.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
//					// dont authenticate this particular request
//					.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/forgot-pass","/api/forgot-pass-confirm","/api/entity","/api/login").permitAll().
//					// all other requests need to be authenticated
//					anyRequest().authenticated().and().httpBasic().and().
//					// make sure we use stateless session; session won't be used to
//					// store user's state.
//					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			// Add a filter to validate the tokens with every request
//			httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//
//		}
//
//		@Bean
//		public CorsConfigurationSource corsConfigurationSource() {
//
//			//System.out.println("corsConfigurationSource");
//			CorsConfiguration configuration = new CorsConfiguration();
//			configuration.setAllowedOrigins(Arrays.asList("*"));
//			configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//			configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//			configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			source.registerCorsConfiguration("/**", configuration);
//			return source;
//
//		}

	  
   
	  
	  
	  
	  
	  
	  
	  
	  
	  
	 
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf() .disable() .authorizeRequests()
	 * .antMatchers("/api/authenticate").permitAll().anyRequest().authenticated(); }
	 */
	/*
	 * @Autowired private MailService mailService;
	 */

	
	/*
	 * @Bean public UserServiceImpl getUserServiceImpl() { return new
	 * UserServiceImpl(); }
	 */
	  
		/*
		 * @Bean public CandidateServiceImpl getCandidateServiceImpl() { return new
		 * CandidateServiceImpl(); }
		 */
}
