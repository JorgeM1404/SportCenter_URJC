package aplicacion;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

import entidades.Usuario;

@Service
public class ServicioUsuarios 
{
	private ConcurrentHashMap<Long,Usuario> usuarios = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	public ServicioUsuarios() {
		nuevoUsuario(new Usuario("Jorge","1234","j.molinat.2017@alumnos.urjc.es"));
	}
	
	public Usuario findById(long id) {
		return usuarios.get(id);
	}
	
	public void removeById(long id) {
		usuarios.remove(id);
	}
	
	public Collection<Usuario> findAll() {
		return usuarios.values();
	}
	
	public void nuevoUsuario(Usuario usu) {
		long id = nextId.getAndIncrement();
		usu.setId(id);
		this.usuarios.put(id, usu);
	}
}
