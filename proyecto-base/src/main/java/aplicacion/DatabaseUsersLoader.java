package aplicacion;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader 
{	
	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	@PostConstruct 
	private void initDatabase() 
	{
		repositorioUsuarios.save(new Usuario("Jorge","1234","j.molinat.2017@alumnos.urjc.es","USER"));
		repositorioUsuarios.save(new Usuario("q","w","e","ADMIN"));		
	}	
}
