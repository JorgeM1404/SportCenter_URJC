package es.urjc.etsii.dad.SportCenterURJC;

public class Usuario {
	
	private String nombre;
	private String contraseña;
	private String correo;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String contraseña, String correo) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.correo = correo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
