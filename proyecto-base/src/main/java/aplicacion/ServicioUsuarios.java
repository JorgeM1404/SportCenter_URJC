package aplicacion;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarios 
{
	@Autowired
	private RepositorioUsuarios repositorio;
	
	public Collection<Usuario> getUsuarios() {
		return repositorio.findAll();
	}
	
	public Usuario getUsuario(long id) {
		return repositorio.findById(id).orElseThrow();		
	}
	
	public Usuario getUsuarioByNombre(String nombre) {
		return repositorio.findByNombre(nombre).orElseThrow();		
	}
	
	public Usuario getUsuarioByCampos(String nombre, String passwordHash, String correo) {
		return repositorio.findByNombreAndPasswordHashAndCorreo(nombre,passwordHash,correo);		
	}
	
	public void borrarUsuario(long id) {
		Usuario usu = repositorio.findById(id).orElseThrow();
		repositorio.delete(usu);
	}
	
	public void guardarUsuario(Usuario usu) {
		repositorio.save(usu);
	}
	
	public boolean existeUsuario(Usuario usu) {
		return repositorio.existsByNombreAndPasswordHashAndCorreo(usu.getNombre(), usu.getPasswordHash(), usu.getCorreo());
	}
}
