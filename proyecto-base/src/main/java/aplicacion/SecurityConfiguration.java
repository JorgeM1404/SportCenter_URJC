package aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// paginas publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/registrarse").permitAll();
		http.authorizeRequests().antMatchers("/usuario/nuevo").permitAll();
		http.authorizeRequests().antMatchers("/iniciarSesion").permitAll();
		http.authorizeRequests().antMatchers("/usuario/acceso").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();
		http.authorizeRequests().antMatchers("/imagenes/**").permitAll();
		http.authorizeRequests().antMatchers("/styles.css").permitAll();
		// paginas privadas
		http.authorizeRequests().anyRequest().authenticated();
		
		// login
		 http.formLogin().loginPage("/iniciarSesion");
		 http.formLogin().usernameParameter("nombre");
		 http.formLogin().passwordParameter("clave");
	   //http.formLogin().passwordParameter("correo");
		 http.formLogin().defaultSuccessUrl("/paginaPrincipal");
		 http.formLogin().failureUrl("/loginerror");
		 
		// Logout
		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		
		// Disable CSRF at the moment
		// http.csrf().disable();
	}
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	*/
}
