/*package aplicacion;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// paginas publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/registrarse").permitAll();
		http.authorizeRequests().antMatchers("/usuario/nuevo").permitAll();
		http.authorizeRequests().antMatchers("/iniciarSesion").permitAll();
		http.authorizeRequests().antMatchers("/usuario/acceso").permitAll();
		http.authorizeRequests().antMatchers("/error").permitAll();
		
		// paginas privadas
		http.authorizeRequests().anyRequest().authenticated();
		
		// login
		 http.formLogin().loginPage("/iniciarSesion");
		 http.formLogin().usernameParameter("nombre");
		 http.formLogin().passwordParameter("clave");
		 http.formLogin().passwordParameter("correo");
		 http.formLogin().defaultSuccessUrl("/campus");
		 http.formLogin().failureUrl("/loginerror");
		 
		// Logout
		 http.logout().logoutUrl("/logout");
		 http.logout().logoutSuccessUrl("/");
		
		// Disable CSRF at the moment
		 http.csrf().disable();
	}
	
}
*/