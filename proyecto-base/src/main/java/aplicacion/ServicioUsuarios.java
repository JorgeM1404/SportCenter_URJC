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
	public void init() {
		repositorio.save(new Usuario("Jorge","1234","j.molinat.2017@alumnos.urjc.es"));
	}
	
	public Collection<Usuario> getUsuarios() {
		return repositorio.findAll();
	}
	
	public Usuario getUsuario(String id) {
		return repositorio.findById(id).orElseThrow();		
	}
	
	public void borrarUsuario(String id) {
		Usuario usu = repositorio.findById(id).orElseThrow();
		repositorio.delete(usu);
	}
	
	public void guardarUsuario(Usuario usu) {
		repositorio.save(usu);
	}
	
	public boolean existeUsuario(Usuario usu) {
		return repositorio.existsByNombreAndClaveAndCorreo(usu.getNombre(), usu.getClave(), usu.getCorreo());
	}
}
