package aplicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import entidades.Actividad;
import entidades.CentroDeportivo;
import entidades.PistaDeportiva;
import entidades.Usuario;

public class ServicioCentroDeportivo 
{
	private ConcurrentHashMap<Long,CentroDeportivo> centros = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	public ServicioCentroDeportivo() 
	{
		PistaDeportiva [] pistas1 = {new PistaDeportiva("Cancha de football A",false),new PistaDeportiva("Cancha de football B",false)};
        PistaDeportiva [] pistas2 = {new PistaDeportiva("Cancha de football A",false),new PistaDeportiva("Cancha de football B",false)};
	    Actividad actividad1 = new Actividad("Football",pistas1);
	    Actividad actividad2 = new Actividad("Football",pistas2);
	    ArrayList<Actividad> actividades = new ArrayList<>(); actividades.add(actividad1); actividades.add(actividad2);
	    
	    nuevoCentro(new CentroDeportivo ("Móstoles",actividades));
	}
	
	public CentroDeportivo findById(long id) {
		return centros.get(id);
	}
	
	public void removeById(long id) {
		centros.remove(id);
	}
	
	public Collection<CentroDeportivo> findAll() {
		return centros.values();
	}
	
	public void nuevoCentro(CentroDeportivo centro) {
		long id = nextId.getAndIncrement();
		centro.setId(id);
		this.centros.put(id, centro);
	}
}
