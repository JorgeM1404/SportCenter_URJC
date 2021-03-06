package aplicacion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "centro")
public class CentroDeportivo 
{
    @Id
	private String campus;
	
    @ManyToMany(mappedBy = "centros")
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Actividad> actividades;

	public CentroDeportivo() { }
	
	public CentroDeportivo(String campus) {
		super();
		this.campus = campus;
		this.actividades = new LinkedList<>();
	}
	
	public CentroDeportivo(String campus, ArrayList<Actividad> actividades) {
		super();
		this.campus = campus;
		this.actividades= actividades;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	/*public void addActividad(Actividad act) {
		actividades.add(act);
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
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
		if (campus == null) {
			if (other.campus != null)
				return false;
		} else if (!campus.equals(other.campus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CentroDeportivo [campus=" + campus + "]";
	}
}