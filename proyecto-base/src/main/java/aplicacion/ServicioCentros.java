package aplicacion;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCentros 
{
	@Autowired
	private RepositorioCentros repositorio;
	
	@PostConstruct
	public void Init()
	{
		/*PistaDeportiva [] pistas1 = {new PistaDeportiva("Cancha de football A",false),new PistaDeportiva("Cancha de football B",false)};
        PistaDeportiva [] pistas2 = {new PistaDeportiva("Cancha de football A",false),new PistaDeportiva("Cancha de football B",false)};
	    Actividad actividad1 = new Actividad("Football",pistas1);
	    Actividad actividad2 = new Actividad("Football",pistas2);
	    ArrayList<Actividad> actividades = new ArrayList<>(); actividades.add(actividad1); actividades.add(actividad2);*/
	    
	    repositorio.save(new CentroDeportivo ("Mostoles"));//,actividades));
	    repositorio.save(new CentroDeportivo ("Alcorcón"));
	    repositorio.save(new CentroDeportivo ("Fuenlabrada"));
	    repositorio.save(new CentroDeportivo ("Aranjuez"));
	    repositorio.save(new CentroDeportivo ("Vicálvaro"));
	}

	public ArrayList<Actividad> getActividadesCentroById(long id) {
		return repositorio.findById(id).orElseThrow().getActividades();
	}
	
	public ArrayList<Actividad> getActividadesCentroByCampus(String campus)	{
		return repositorio.findByCampus(campus).orElseThrow().getActividades();
	}
	
	
	public CentroDeportivo getCentroById(long id) {
		return repositorio.findById(id).orElseThrow();
	}
	
	public CentroDeportivo getCentroByCampus(String campus) {
		return repositorio.findByCampus(campus).orElseThrow();
	}
	
	public void borrarCentroById(long id) {
		repositorio.deleteById(id);
	}
	
	public void borrarCentroByCampus(String campus) {
		repositorio.deleteByCampus(campus);
	}
	
	public Collection<CentroDeportivo> findAll() {
		return repositorio.findAll();
	}
	
	/*public void guardarCentro(CentroDeportivo centro) {
		repositorio.save(centro);
	}*/
}
