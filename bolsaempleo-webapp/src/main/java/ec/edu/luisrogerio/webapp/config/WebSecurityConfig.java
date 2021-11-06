package ec.edu.luisrogerio.webapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ec.edu.luisrogerio.service.crud.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	String[] resources = new String[] { "/resources/**", "/javax.faces.resource/**" };
	String[] registros = new String[] { "/candidato/**", "/empleador/**" };
	String[] mensajes = new String[] { "/success.xhtml", "/successpassword.xhtml","/404.xhtml" };

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(resources).permitAll();
		http.authorizeRequests().antMatchers(registros).permitAll();
		http.authorizeRequests().antMatchers(mensajes).permitAll();
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/wp-admin/admin/**").access("hasRole('ADMIN')");
		http.authorizeRequests().antMatchers("/wp-admin/candidato/**").access("hasRole('CANDIDATO')");
		http.authorizeRequests().antMatchers("/wp-admin/empleador/**").access("hasRole('EMPLEADOR')");
		http.authorizeRequests().anyRequest().authenticated().and();
		http.formLogin()
				.loginPage("/login.xhtml")
				.permitAll()
				//.defaultSuccessUrl("/redirect.xhtml")
				.successHandler(new AuthenticationSuccessHandler() {
				    @Override
				    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				            Authentication authentication) throws IOException, ServletException {
				        redirectStrategy.sendRedirect(request, response, "/redirect.xhtml");
				    }
				})
				.failureUrl("/login.xhtml?error=true")
				.usernameParameter("username")
                .passwordParameter("password")
                .and();
		http.logout()
				.permitAll()
        .		logoutSuccessUrl("/login.xhtml?logout");

		http.exceptionHandling().accessDeniedPage("/accessDenied.xhtml");
		http.csrf().disable();
		http.httpBasic().realmName("bolsaempleo");

		http.sessionManagement().maximumSessions(1).expiredUrl("/login.xhtml");
	}

	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;
	}

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}