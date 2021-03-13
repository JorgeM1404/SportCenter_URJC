package aplicacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReservaController {
	@Autowired
	private ServicioReservas servicioReservas;
	@Autowired
	private ServicioActividades servicioActividades;
	@Autowired
	private ServicioCentroActual servicioCentroActual;
	
	//private Usuario usuarioActual;
	//private CentroDeportivo centroActual;
	
	
	@GetMapping("/perfil/cancelarReserva/{id}")
	public String cancelarReservaGet(Model model, @PathVariable long id)
	{
		Reserva res = servicioReservas.getReservaById(id);
		model.addAttribute("res",res);
		return "cancelarReserva";
	}
	
	@GetMapping("/perfil/realizarReserva")
	public String solicitarReservaGet()
	{
		return "realizarReserva";
	}
	
	@GetMapping("/mostrar_perfil")
	public String mostrarPerfil(Model model, HttpSession sesion)
	{
	Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
	List<Reserva> reservas = usuarioActual.getReservas();
	model.addAttribute("usu", usuarioActual);
	model.addAttribute("res", reservas);
	return "mostrar_perfil";
	}
	
	@PostMapping("/perfil/realizarReserva")
	public void solicitarReservapsot(Model model,Reserva res, @RequestParam String nombreActividad, HttpSession sesion,  HttpServletResponse response) throws IOException
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		long id = 0;// Actividad act = servicioActividades.getActividadByNombre(nombreActividad);
		
		List<Actividad> actCentro = centroActual.getActividades();
		for(Actividad a : actCentro)
		{
			if(a.getNombreActividad().equalsIgnoreCase(nombreActividad))
			{
				id = a.getId();
			}
		}
		Actividad act = servicioActividades.getActividadById(id);
		
		List<Reserva> reservas = usuarioActual.getReservas();
		
		res.setUsuario(usuarioActual);
		res.setActividadRes(act);
		res.setCentro(centroActual);
		reservas.add(res);
		servicioReservas.guardarReserva(res);
		
		response.sendRedirect("/mostrar_perfil");
	}
	
	@GetMapping("/perfil/reservaCancelada/{id}")
	public String miPerfil2(Model model, @PathVariable long id, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		reservas.remove(servicioReservas.getReservaById(id));
		servicioReservas.cancelarReservaById(id);
		
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
}
