package aplicacion;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseUsersLoader {
	
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	@PostConstruct 
	private void initDatabase() {
		
		//repositorioUsuarios.save(new User("user","pass","ROLE_USER"));
		//repositorioUsuarios.save(new User("admin","adminpass","ROLE_USER","ROLE_ADMIN"));
		
	}
	
}
