package com.barclays.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class UserServiceSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				//authorizeRequests().anyRequest().permitAll()
				//.antMatchers("/course/register").permitAll();
				authorizeRequests()
					.antMatchers("/user/register").permitAll()
				/*
				 * .antMatchers("/user-service/get/registerrequests").hasRole("ADMIN")
				 * .antMatchers("/user-service/actionforregistration/{userRequestId}").hasRole(
				 * "ADMIN") .antMatchers("/user-service/reset/password").hasRole("USER")
				 * .antMatchers("/user-service/actionformakedisableorenable/{userId}").hasRole(
				 * "ADMIN")
				 * .antMatchers("/userwisesubscription/{userId}").hasAnyRole("USER","ADMIN")
				 * .antMatchers("/user/get/{userId}").hasAnyRole("USER","ADMIN")
				 * .antMatchers("/user/getall").hasRole("ADMIN")
				 * .antMatchers("/user/delete/{userId}").hasRole("ADMIN")
				 */
					.and() .formLogin()
					.and().csrf().disable();
		/*
		 * http. headers() .frameOptions().disable();
		 */
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
