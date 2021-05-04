package aplicacion;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="cacheURJC")
@Service
public class ServicioCentroActual 
{
	private CentroDeportivo centroActual;

	@Cacheable
	public CentroDeportivo getCentroActual() {
		return centroActual;
	}

	public void setCentroActual(CentroDeportivo centroActual) {
		this.centroActual = centroActual;
	}
}
