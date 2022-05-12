package br.com.alura.mvc.mudi;


import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final DataSource dataSource;

	public WebSecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated()
		.and()
		.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
        )
		.logout(logout -> logout.logoutUrl("/logout"));
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
		
		
			auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(encoder);
	}
	
	
}
