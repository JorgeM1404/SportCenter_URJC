package aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter
{
	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{	
		// paginas publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/registrarse").permitAll();
		http.authorizeRequests().antMatchers("/usuario/nuevo").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
		http.authorizeRequests().antMatchers("/imagenes/**").permitAll();
		http.authorizeRequests().antMatchers("/styles.css").permitAll();
		
		// paginas privadas
		http.authorizeRequests().antMatchers("/gestion", "/gestion/**").hasAnyRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		
		// login
		 http.formLogin().loginPage("/login");
		 http.formLogin().usernameParameter("nombre");
		 http.formLogin().passwordParameter("clave");
		 http.formLogin().defaultSuccessUrl("/menuPrincipal");
		 http.formLogin().failureUrl("/loginerror");
		 
		// Logout
		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		
		// Disable CSRF at the moment
		//http.csrf().disable();
	}	
}
