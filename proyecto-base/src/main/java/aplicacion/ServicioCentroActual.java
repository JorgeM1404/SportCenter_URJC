package aplicacion;

import org.springframework.stereotype.Service;

@Service
public class ServicioCentroActual {

	private CentroDeportivo centroActual;

	public CentroDeportivo getCentroActual() {
		return centroActual;
	}

	public void setCentroActual(CentroDeportivo centroActual) {
		this.centroActual = centroActual;
	}
}
