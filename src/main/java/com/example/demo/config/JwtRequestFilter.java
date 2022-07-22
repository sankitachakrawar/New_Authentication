package com.example.demo.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.dto.CandidateDto;
import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.JwtUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtTokenUtil jwtToUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token=null;
		String username=null;
		String authorizationHeader=request.getHeader("Authorization");
		//MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(request);
		JsonObject jsonObject = null;
		
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			token=authorizationHeader.substring(7);
			
			try {
			username=jwtToUtil.getEmailFromToken(token);
			System.out.println("TOKEN "+ JsonParser.parseString(username));
//			jsonObject = JsonParser.parseString(username).getAsJsonObject();
			} catch (Exception e) {

				new Exception(e.getMessage());

			}	
		}else {

			logger.warn("JWT Token does not begin with Bearer String");

		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
			System.out.println("JSONOBJECT  :"+ jsonObject);
			UserDetails userDetails=customUserDetailsService.loadUserByEmail(JsonParser.parseString(username).toString());
			
//			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jsonObject.get("email").getAsString());

			System.out.println("GET EMAIL: "+ userDetails);
			
			if(jwtToUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
//				CandidateDto userData = new CandidateDto();
//				userData.setId((jsonObject.get("id").getAsLong()));
//				userData.setName(jsonObject.get("name").getAsString());
//				userData.setEmail(jsonObject.get("email").getAsString());
//				multiReadRequest.setAttribute("userData", userData);
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
