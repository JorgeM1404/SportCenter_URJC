package entidades;

public class PistaDeportiva {

	private long id;
	
	private String nombre_pista;
	private Actividad actividad;
	private boolean ocupado;

	public PistaDeportiva() {
		
	}
	
	public PistaDeportiva(String nombre_pista, Actividad actividad, boolean ocupado ) {
		this.nombre_pista = nombre_pista;
		this.actividad = actividad;
		this.ocupado = ocupado;
	}
	
	public String getNombrePista() {
		return nombre_pista;
	}

	public void setNombrePista(String nombre_pista) {
		this.nombre_pista = nombre_pista;
	}
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	public boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado() {
		this.ocupado = true;
	}
	
	public void setLibre() {
		this.ocupado = false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
