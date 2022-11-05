package com.gl.emp.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.emp.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	
         authenticationManagerBuilder
                 .userDetailsService(this.userDetailsService)
                 .passwordEncoder(passwordEncoder());

	}
	
	
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.cors().disable();
	        httpSecurity.csrf().disable();
	        httpSecurity.headers().frameOptions().disable();
	        httpSecurity
	                .authorizeRequests()
	                	.antMatchers(GET,"/api/employees/**")
	                	.permitAll()
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST,"/api/employees/**")
	                	.hasRole("ADMIN")
	                .antMatchers(HttpMethod.PUT,"/api/employees/**")
	                	.hasRole("ADMIN")
	                .antMatchers(HttpMethod.DELETE,"/api/employees/**")
	                	.hasRole("ADMIN")
	                .anyRequest()
	                	.authenticated()
	                .and()
	                	.formLogin()
	                .and()
	                	.httpBasic()
	               .and()
	                .sessionManagement().sessionCreationPolicy(STATELESS);
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }


}


