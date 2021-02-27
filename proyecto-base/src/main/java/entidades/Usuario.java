package entidades;

public class Usuario 
{
	private long id;
	
	private String nombre;
	private String clave;
	private String correo;
	
	public Usuario() {
		
	}
	
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
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(getClass() != o.getClass()) return false;
		
		final Usuario u = (Usuario) o;
		
		if( !this.getNombre().equals(u.getNombre()) || !this.getClave().equals(u.getClave()) || !this.getCorreo().equals(u.getCorreo()) ) return false;
		else return true;
	}
}
