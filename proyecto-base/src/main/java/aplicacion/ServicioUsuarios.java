package aplicacion;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioUsuarios 
{
	@Autowired
	private RepositorioUsuarios repositorio;
	
	@Cacheable
	public Collection<Usuario> getUsuarios() {
		return repositorio.findAll();
	}
	
	@Cacheable
	public Usuario getUsuario(long id) {
		return repositorio.findById(id).orElseThrow();		
	}
	
	@Cacheable
	public Usuario getUsuarioByNombre(String nombre) {
		return repositorio.findByNombre(nombre).orElseThrow();		
	}
	
	@Cacheable
	public Usuario getUsuarioByCampos(String nombre, String passwordHash, String correo) {
		return repositorio.findByNombreAndPasswordHashAndCorreo(nombre,passwordHash,correo);		
	}
	
	@CacheEvict(allEntries = true)
	public void borrarUsuario(long id) {
		Usuario usu = repositorio.findById(id).orElseThrow();
		repositorio.delete(usu);
	}
	
	@CacheEvict(allEntries = true)
	public void guardarUsuario(Usuario usu) {
		repositorio.save(usu);
	}
	
	@Cacheable
	public boolean existeUsuario(Usuario usu) {
		return repositorio.existsByNombreAndPasswordHashAndCorreo(usu.getNombre(), usu.getPasswordHash(), usu.getCorreo());
	}
}
