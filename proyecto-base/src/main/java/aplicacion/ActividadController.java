package aplicacion;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActividadController 
{	
	@Autowired
	private ServicioCentros servicioCentros;
	@Autowired
	private ServicioActividades servicioActividades;
	@Autowired
	private ServicioPistas servicioPistas;
	@Autowired
	private ServicioReservas servicioReservas;
	@Autowired
	private ServicioCentroActual servicioCentroActual;
	
	@GetMapping("/gestion")
	public String gestionActividad()
	{
		return "gestion";
	}
	
	@GetMapping("/gestion/crear")
	public String crearActividad(Model model, HttpServletRequest request)
	{
		List<CentroDeportivo> centros = servicioCentros.findAll();
		model.addAttribute("centros",centros);
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());
		
		return "crearActividad";
	}
	
	@GetMapping("/gestion/borrar")
	public String borrarActividad()
	{
		return "gestion";
	}
	
	@PostMapping("/gestion/crear")
	public String crearNuevaActividad(Model model, @RequestParam String nombreActividad, @RequestParam String nombreCentro, @RequestParam String nombrePista)
	{
		PistaDeportiva p = new PistaDeportiva(nombrePista,false);
		Actividad a = new Actividad(nombreActividad,Arrays.asList(p));
		CentroDeportivo c = servicioCentros.getCentro(nombreCentro);
		c.getActividades().add(a);
		a.getCentros().add(c);
		p.setActividad(a);
		
		servicioActividades.guardarActividad(a);
		servicioPistas.guardarPista(p);
		
		return "gestion";
	}
	
	@GetMapping("/gestion/borrar/{id}")
	public String borrarActividadExistente(Model model, @PathVariable long id)
	{		
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		Actividad act = servicioActividades.getActividadById(id);
        model.addAttribute("act", act);    
        model.addAttribute("centro", centroActual);
		
		return "borrarActividad";
	}
	
	@GetMapping("/gestion/borrada/{id}")
	public String borradaActividadExistente(Model model, @PathVariable long id, HttpSession sesion)
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

		Actividad act = servicioActividades.getActividadById(id);
		List<Reserva> reservasUsu = usuarioActual.getReservas();

		Reserva borrar = null;

		for(Reserva res : reservasUsu)
			{
				if(res.getActividadRes().equals(act))
				{
					res.setUsuario(null);
					res.setActividadRes(null);
					res.setCentro(null);
					borrar = res;
				}
			}
		if(borrar != null)
		{
			reservasUsu.remove(borrar);
			servicioReservas.cancelarReserva(borrar);
		}

		//Actividad act = servicioActividades.getActividadById(id);
		List<PistaDeportiva> pistas = act.getPistas();

		String plantilla = "";
		List<Actividad> actividades = centroActual.getActividades();

		for(PistaDeportiva p: pistas) p.setActividad(null);
		act.setPistas(null);
		act.getCentros().remove(centroActual);
		servicioActividades.borrarActividadById(id);

		actividades.remove(act);

		model.addAttribute("centro", centroActual);
		model.addAttribute("act", actividades);

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
}
