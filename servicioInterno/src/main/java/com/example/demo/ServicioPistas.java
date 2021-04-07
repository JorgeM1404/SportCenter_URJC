package aplicacion;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPistas 
{
	@Autowired RepositorioPistas repositorio;
	
	public Collection<PistaDeportiva> getPistas() {
		return repositorio.findAll();
	}
	
	public PistaDeportiva getPistaByNombre(String nombre) {
		return repositorio.findByPista(nombre).orElseThrow();
	}
	
	public void borrarPistaByNombre(String nombre) {
		PistaDeportiva pd = repositorio.findByPista(nombre).orElseThrow();
		repositorio.delete(pd);
	}
	
	public PistaDeportiva getPistaById(long id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public void borrarPistaById(long id) {
		PistaDeportiva pd = repositorio.findById(id).orElseThrow();
		repositorio.delete(pd);
	}
	
	public void guardarPista(PistaDeportiva pd) {
		repositorio.save(pd);
	}	
	
	public void guardarPistas(List<PistaDeportiva> pds) {
		repositorio.saveAll(pds);
	}
}
