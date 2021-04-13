package serviciointerno;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pistas")
public class PistaController 
{
	@Autowired
	private RepositorioPistas repositorio;
	
	@GetMapping("/")
	public ResponseEntity<List<PistaDeportiva>> getUsuarios() {
		List<PistaDeportiva> pistas = repositorio.findAll();
		
		if(pistas.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		return new ResponseEntity<>(pistas, HttpStatus.OK);
	}
	
	
	/*@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PistaDeportiva> nuevaPista(@RequestBody PistaDeportiva pista) {
		if(usuario != null && repositorio.findByNombreAndPasswordHashAndCorreo(usuario.getNombre(), usuario.getPasswordHash(), usuario.getCorreo()) != null) {
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
		else {
			usuario.setReservas(new LinkedList<Reserva>());
			usuario.getRoles().add("USER");
			//usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPasswordHash()));
		    repositorio.save(usuario);
		    return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
		}
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<PistaDeportiva> getPista(@PathVariable long id) {
		Optional<PistaDeportiva> pista = repositorio.findById(id);
		if(pista.isPresent()) {
			return new ResponseEntity<>(pista.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/comprobarPista/{id}")
	public boolean comprobarPista(@PathVariable long id) {
		Optional<PistaDeportiva> pista = repositorio.findById(id);
		if(pista.isPresent()) {
			if(pista.get().getOcupado() == true)
			{
				return false;
			}
			return true;
		}
		return false;
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> borrarUsuario(@PathVariable long id) {
		Optional<Usuario> usuario = repositorio.findById(id);
		if(usuario.isPresent()) {
			repositorio.deleteById(id);
			return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actulizaUsuario(@PathVariable long id, @RequestBody Usuario usuarioAct) {
		Optional<Usuario> usuario = repositorio.findById(id);
		if(usuario.isPresent()) {
			usuario.get().setNombre(usuarioAct.getNombre());
			usuario.get().setPasswordHash(usuarioAct.getPasswordHash());
			usuario.get().setCorreo(usuarioAct.getCorreo());
			usuario.get().setReservas(usuarioAct.getReservas());
			return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}*/

}
