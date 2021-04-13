package aplicacion;

public class InfoReserva {
	private String nombre;
	private String campus;
	private String actividad;
	private String fecha;
	public InfoReserva(String nombre, String campus, String actividad, String fecha)
	{
		this.nombre = nombre;
		this.campus = campus;
		this.actividad = actividad;
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
