package aplicacion;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CentroDeportivo 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String campus;
	private ArrayList<Actividad> actividades;

	public CentroDeportivo() { }
	
	public CentroDeportivo(String campus) {
		this.campus = campus;
	}
	
	public CentroDeportivo(String campus, ArrayList<Actividad> actividades) {
		this.campus = campus;
		this.actividades= actividades;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CentroDeportivo other = (CentroDeportivo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CentroDeportivo [id=" + id + ", campus=" + campus + ", actividades=" + actividades + "]";
	}
}
