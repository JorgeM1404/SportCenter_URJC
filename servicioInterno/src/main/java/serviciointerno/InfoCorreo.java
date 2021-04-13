package serviciointerno;

public class InfoCorreo {
	
	private String desde;
	private String para;
	private String asunto;
	private String cuerpo;

	public InfoCorreo(String desde, String para, String asunto, String cuerpo)
	{
		this.desde = desde;
		this.para = para;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	
}
