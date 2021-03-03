package aplicacion;

import java.util.Collection;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarios 
{
	@Autowired
	private RepositorioUsuarios repositorio;
	
	@PostConstruct
	public void init()
	{
		repositorio.save(new Usuario("Jorge","1234","j.molinat.2017@alumnos.urjc.es"));
	}
	
	public Collection<Usuario> getUsuarios()
	{
		return repositorio.findAll();
	}
	
	public Usuario getUsuario(long id)
	{
		return repositorio.findById(id).orElseThrow();		
	}
	
	public void borrarUsuario(long id)
	{
		Usuario usu = repositorio.findById(id).orElseThrow();
		repositorio.delete(usu);
	}
	
	public void guardarUsuario(Usuario usu)
	{
		repositorio.save(usu);
	}
}
