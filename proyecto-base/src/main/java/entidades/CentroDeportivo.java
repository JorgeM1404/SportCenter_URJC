package entidades;

import java.util.ArrayList;

public class CentroDeportivo {

	private long id;
	
	private String campus;
	private ArrayList<Actividad> actividades;

	public CentroDeportivo() {
		
	}
	
	public CentroDeportivo(String campus, ArrayList<Actividad> actividades) {
		this.campus = campus;
		this.actividades= actividades;
	}
	
	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
