package aplicacion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CacheConfig(cacheNames="cacheURJC")
@Controller
public class CentroController 
{
	@Autowired
	private ServicioCentros servicioCentros;
	@Autowired
	private ServicioActividades servicioActividades;
	@Autowired
	private ServicioCentroActual servicioCentroActual;
	
	@GetMapping("/campus")
	public String menuCampus(Model model)
	{
		return "paginaPrincipal";
	}
	
	@Cacheable
	@GetMapping("/campus/{campus}")
	public String SeleccionarCampus(Model model, @PathVariable String campus)
	{	
		String plantilla = "";
		CentroDeportivo centroActual = servicioCentros.getCentro(campus);//.getCentroByCampus(campus);
		servicioCentroActual.setCentroActual(centroActual);
		List<Actividad> act = centroActual.getActividades();
		
		model.addAttribute("centro", centroActual);
		model.addAttribute("act", act);
		
		switch (campus) 
		{
			case "Mostoles": plantilla = "/imagenes/urjc_mostoles.jpg"; break;	
			case "Alcorcón": plantilla = "/imagenes/urjc_alcorcon.jpg"; break;	
			case "Fuenlabrada": plantilla = "/imagenes/urjc_fuenlabrada.jpg"; break;	
			case "Aranjuez": plantilla = "/imagenes/urjc_aranjuez.jpg"; break;	
			case "Vicálvaro": plantilla = "/imagenes/urjc_vicalvaro.jpg"; break;	
		}	
		model.addAttribute("plantilla", plantilla);
		return "campus";
	}
	
	@Cacheable
	@GetMapping("/campus/actividades")
	public String menuActividades(Model model)
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		String plantilla = "";
		List<Actividad> act = centroActual.getActividades();
		model.addAttribute("centro", centroActual);
		model.addAttribute("act", act);
		switch (centroActual.getCampus())  
		{
			case "Mostoles": plantilla = "/imagenes/urjc_mostoles.jpg"; break;	
			case "Alcorcón": plantilla = "/imagenes/urjc_alcorcon.jpg"; break;	
			case "Fuenlabrada": plantilla = "/imagenes/urjc_fuenlabrada.jpg"; break;	
			case "Aranjuez": plantilla = "/imagenes/urjc_aranjuez.jpg"; break;	
			case "Vicálvaro": plantilla = "/imagenes/urjc_vicalvaro.jpg"; break;
		}	
		model.addAttribute("plantilla", plantilla);
		return "campus";
	}
	
	@Cacheable
	@GetMapping("/campus/actividades/{id}")
	public String SeleccionarActividad(Model model, @PathVariable long id, HttpServletRequest request)
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		Actividad act = servicioActividades.getActividadById(id);
		List<CentroDeportivo> centros = act.getCentrosSalvo(centroActual);
		List<PistaDeportiva> pistas = act.getPistas();
		
		model.addAttribute("pistas", pistas);
		model.addAttribute("centros", centros);
		model.addAttribute("act", act);
		
		model.addAttribute("admin",request.isUserInRole("ADMIN"));
		
		return "actividad";
	}
}
