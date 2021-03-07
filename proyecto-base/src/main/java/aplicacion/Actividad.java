package aplicacion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "actividad")
public class Actividad 
{
	@Id	
	private String nombre;
	
	@OneToMany(mappedBy="actividad")
	private List<PistaDeportiva> pistas;
	
	//private int plazas;
	
	@ManyToMany
	private List<CentroDeportivo> centros;
	
	public Actividad() { }
	
	public Actividad(String nombre, List<PistaDeportiva>pistas) {
		super();
		this.nombre = nombre;
		this.pistas = pistas;
		this.centros = new LinkedList<>();
	}
	
	public Actividad(String nombre) {
		super();
		this.nombre = nombre;
		this.pistas = new LinkedList<>();
		this.centros =  new LinkedList<>();
	}

	public String getNombreActividad() {
		return nombre;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombre = nombreActividad;
	}

	public List<PistaDeportiva> getPistas() {
		return pistas;
	}

	public void setPistas(List<PistaDeportiva> pistas) {
		this.pistas = pistas;
	}

	/*public Int getPlazas() {
		return plazas;
	}

	public void setPlazas(Int plazas) {
		this.plazas = plazas;
	}*/
	
	/*public void addCentro(CentroDeportivo centro)
	{
		centro.addActividad(this);
		centros.add(centro);
	}*/
	
	public List<CentroDeportivo> getCentros() {
		return centros;
	}
	
	public List<CentroDeportivo> getCentrosSalvo(CentroDeportivo centro) {
		List<CentroDeportivo> cens = new LinkedList<>();
		for(CentroDeportivo c: centros) if(!c.getCampus().equals(centro.getCampus())) cens.add(c);
		return cens;
	}

	public void setCentros(ArrayList<CentroDeportivo> centro) {
		this.centros = centro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actividad other = (Actividad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + "]";
	}
}