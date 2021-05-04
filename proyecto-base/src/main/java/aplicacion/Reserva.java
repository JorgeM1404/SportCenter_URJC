package aplicacion;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Reserva 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombreReserva;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date fecha;
	
	@ManyToOne
	private Usuario usuarioRes;
	
	@OneToOne
	private Actividad actividadRes;
	
	@OneToOne
	private CentroDeportivo centro;	
	
	public Reserva() { }
	
	public Reserva(String nombreReserva, Date fecha) {
		super();
		this.nombreReserva = nombreReserva;
		this.fecha = fecha;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombreReserva() {
		return nombreReserva;
	}

	public void setNombreReserva(String nombreReserva) {
		this.nombreReserva = nombreReserva;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuarioRes;
	}
	
	public void setUsuario(Usuario usuarioRes) {
		this.usuarioRes = usuarioRes;
	}
	
	public Actividad getActividadRes() {
		return actividadRes;
	}

	public void setActividadRes(Actividad actividadRes) {
		this.actividadRes = actividadRes;
	}
	
	public CentroDeportivo getCentro() {
		return centro;
	}

	public void setCentro(CentroDeportivo centro) {
		this.centro = centro;
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
		Reserva other = (Reserva) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", nombreReserva=" + nombreReserva + ", fecha=" + fecha + "] " + usuarioRes + " " + actividadRes;
	}
}
