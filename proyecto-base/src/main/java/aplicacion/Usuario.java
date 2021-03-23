package aplicacion;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario 
{
	@Id
	private String nombre;
	
	private String clave;
	private String passwordHash;
	private String correo;
	/*
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> roles;
	*/
	@OneToMany(mappedBy="usuarioRes",fetch=FetchType.EAGER)
	private List<Reserva> reservas;
	
	public Usuario() {}
	
	public Usuario(String nombre, String passwordHash, String correo)
	{
		super();
		this.nombre = nombre;
		this.passwordHash = passwordHash;
		this.correo = correo;
		this.reservas = new LinkedList<Reserva>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	 */
	
	public String getCorreo() {
		return correo;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", clave=" + passwordHash + ", correo=" + correo + "]";
	}
}
