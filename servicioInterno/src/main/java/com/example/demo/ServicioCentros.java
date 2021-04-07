package aplicacion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCentros 
{
	@Autowired
	private RepositorioCentros repositorio;	
	
	public List<Actividad> getActividadesCentro(String id) {
		return repositorio.findById(id).orElseThrow().getActividades();
	}
	
	public CentroDeportivo getCentro(String id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public void borrarCentro(String id) {
		repositorio.deleteById(id);
	}
	
	public List<CentroDeportivo> findAll() {
		return repositorio.findAll();
	}
}
