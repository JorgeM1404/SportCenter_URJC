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
	
	/*private ArrayList<PistaDeportiva> pistas;
	private Reserva reserva;*/	
	
	@ManyToMany
	private List<CentroDeportivo> centros;
	
	public Actividad() { }
	
	public Actividad(String nombre, ArrayList<PistaDeportiva> pistas, Reserva reserva) {
		super();
		this.nombre = nombre;
		//this.pistas = pistas;
		//this.reserva = reserva;
	}
	
	public Actividad(String nombre, ArrayList<PistaDeportiva>pistas) {
		super();
		this.nombre = nombre;
		//this.pistas = pistas;
	}
	
	public Actividad(String nombre) {
		super();
		this.nombre = nombre;
		this.centros =  new LinkedList<>();
	}

	public String getNombreActividad() {
		return nombre;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombre = nombreActividad;
	}

	/*public ArrayList<PistaDeportiva> getPistas() {
		return pistas;
	}

	public void setPistas(ArrayList<PistaDeportiva> pistas) {
		this.pistas = pistas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}*/
	
	public List<CentroDeportivo> getCentros() {
		return centros;
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