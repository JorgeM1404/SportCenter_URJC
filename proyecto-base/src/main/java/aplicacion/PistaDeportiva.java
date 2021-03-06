package aplicacion;

import javax.persistence.*;

@Entity
public class PistaDeportiva 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String pista;
	
	//@Transient
	private boolean ocupado;
	
	@ManyToOne
	private Actividad actividad;

	public PistaDeportiva() { }	
	
	public PistaDeportiva(String pista, boolean ocupado) {
		super();
		this.pista = pista;
		this.ocupado = ocupado;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getNombrePista() {
		return pista;
	}

	public void setNombrePista(String pista) {
		this.pista = pista;
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
		PistaDeportiva other = (PistaDeportiva) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PistaDeportiva [id=" + id + ", nombrePista=" + pista + ", ocupado=" + ocupado + "]";
	}
}
