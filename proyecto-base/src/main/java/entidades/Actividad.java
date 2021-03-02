package entidades;

import java.util.ArrayList;

public class Actividad {

	private long id;
	
	private String nombre_actividad;
	private PistaDeportiva [] pistas;
	private Reserva reserva;

	public Actividad() {
		
	}
	
	public Actividad(String nombre_actividad, PistaDeportiva [] pistas, Reserva reserva) {
		this.nombre_actividad = nombre_actividad;
		this.pistas = pistas;
		this.reserva = reserva;
	}
	
	public Actividad(String nombre_actividad, PistaDeportiva [] pistas) {
		this.nombre_actividad = nombre_actividad;
		this.pistas = pistas;
	}
	
	public String getNombre_actividad() {
		return nombre_actividad;
	}

	public void setCampus(String nombre_actividad) {
		this.nombre_actividad = nombre_actividad;
	}
	
	public PistaDeportiva [] getPistas() {
		return pistas;
	}		
	
	public void setPistas(PistaDeportiva [] pistas) {
		this.pistas = pistas;
	}
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
