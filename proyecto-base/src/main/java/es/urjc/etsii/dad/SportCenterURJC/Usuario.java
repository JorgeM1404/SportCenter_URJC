package es.urjc.etsii.dad.SportCenterURJC;

public class Usuario {
	
	private String nombre;
	private String clave;
	private String correo;
	
	public Usuario(String nombre, String clave, String correo) {
		this.nombre = nombre;
		this.clave = clave;
		this.correo = correo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContraseña() {
		return clave;
	}

	public void setContraseña(String contraseña) {
		this.clave = contraseña;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/*@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null) return false;
		if(getClass() != o.getClass()) return false;
		
		Usuario u = (Usuario) o;
		
		return ( (this.getNombre() == u.getNombre()) && (this.getContraseña() == u.getContraseña()) && (this.getCorreo() == u.getCorreo()) );
	}*/
}
