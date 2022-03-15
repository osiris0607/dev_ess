package com.anchordata.webframework.base.common.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CompositeFilter;

import com.anchordata.webframework.base.common.auth.service.AuthService;
import com.anchordata.webframework.base.common.security.handler.LoginFailureHandler;
import com.anchordata.webframework.base.common.security.handler.LoginSuccessHandler;
import com.anchordata.webframework.base.common.security.handler.OAuth2SuccessHandler;
import com.anchordata.webframework.base.common.security.provider.AuthenticationProvider;
import com.anchordata.webframework.base.util.ClientResources;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Autowired
	LoginSuccessHandler loginSuccessHandler;

	@Autowired
	LoginFailureHandler loginFailureHandler;

	@Autowired
	private AuthService authService;

	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	@Value("${resources.uri.video}")
	private String uriVideo;

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.google")
	public ClientResources google() {
		return new ClientResources();
	}

//	@Value("${namu.boot.upload-path}")
//	private String defaultPath;

	/*
	 * WebSecurity Configuration
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}

	/*
	 * HttpSecurity Configuration
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().disable().csrf().disable();

//		http.authorizeRequests()
//			.antMatchers("/user/login/adminLogin").access("hasIpAddress('172.20.50.83')");

		http.authorizeRequests()
	        .antMatchers("/member/**").access("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
	        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
	        //.antMatchers("/admin/**").access("hasIpAddress('172.20.50.83')");
		
		http.antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/login", "/login/kakao/**", "/login/google", "/kakaoLogin", "/naverLogin", "/verifyEmail", "/signUp", "/error",
						"/user/**", "/gate", "/gateMobile").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);

		// login
		http.formLogin().loginPage("/login")
//			.defaultSuccessUrl("/user/member/loginSuccess")
//			.failureUrl("/user/member/loginFail")
			.failureHandler(loginFailureHandler)
			.successHandler(loginSuccessHandler)
//       .loginProcessingUrl("/login")
				.usernameParameter("member_id").passwordParameter("pwd").permitAll();

		// logout
		http.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/member/login")
				.permitAll();
	}

	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(google(), "/authCallback")); // 이전에 등록했던 OAuth 리다이렉트 URL
		// filters.add(ssoFilter(facebook(), "/login/facebook"));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(restTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		tokenServices.setRestTemplate(restTemplate);
		filter.setTokenServices(tokenServices);
		filter.setAuthenticationSuccessHandler(new OAuth2SuccessHandler("google", authService));
		return filter;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	/*
	 * AuthenticationManager Configuration
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
//		auth.authenticationProvider(authenticationProvider());
	}

	/**
	 * AuthenticationProvider Configuration
	 * 
	 * @return
	 */
	@Bean
	protected AuthenticationProvider authenticationProvider() {
		AuthenticationProvider provider = new AuthenticationProvider();
		provider.setUserDetailsService(authService);
		provider.setPasswordEncoder(passwordEncoder());
		provider.setHideUserNotFoundExceptions(false);
		return provider;
	}

	/**
	 * PasswordEncoder configuration
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
