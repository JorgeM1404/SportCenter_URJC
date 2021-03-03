package aplicacion;

import java.util.ArrayList;

public class Reserva {
	
	private long id;
	
	private Actividad actividadReservada;
	
	private ArrayList<Usuario> usuarios;
	private int num_reservas;
	
	private static final int MAX_PLAZAS = 40;
	
	
	public Reserva() {
		
	}
	
	public Reserva(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Reserva(Actividad actividad, Usuario usuario) {
		this.actividadReservada = actividad;
		usuarios.add(usuario);
	}
	
	
	public int getPlazas()
	{
		return MAX_PLAZAS;
	}
	
	
	
	public ArrayList<Usuario> getUsuario() {
		return usuarios;
	}		
	
	public void setUsuario(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
