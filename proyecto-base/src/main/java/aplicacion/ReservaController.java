package aplicacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReservaController 
{
	@Autowired
	private ServicioReservas servicioReservas;
	@Autowired
	private ServicioActividades servicioActividades;
	@Autowired
	private ServicioCentroActual servicioCentroActual;
	
	@GetMapping("/perfil/cancelarReserva/{id}")
	public String cancelarReservaGet(Model model, @PathVariable long id)
	{
		Reserva res = servicioReservas.getReservaById(id);
		model.addAttribute("res",res);
		return "cancelarReserva";
	}
	
	@GetMapping("/perfil/realizarReserva")
	public String solicitarReservaGet(Model model, HttpServletRequest request)
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		List<Actividad> actsCentro = centroActual.getActividades(); 
		
		/*SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());*/
		
		LocalDate date = LocalDate.now();
		
		model.addAttribute("actsCentro",actsCentro);
		model.addAttribute("date",date);
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());
	    
		return "realizarReserva";
	}
	
	@GetMapping("/mostrar_perfil")
	public String volverAPerfil(Model model, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		
		return "mostrar_perfil";
	}
	
	@GetMapping("/descargaPDF")
	public String descargarPDF(Model model, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/pdf/enviar";
		
		List<InfoReserva> infoReservas = new LinkedList<>();
		for(Reserva r : reservas)
		{
			infoReservas.add(new InfoReserva(r.getNombreReserva(),r.getCentro().getCampus(),r.getActividadRes().getNombreActividad(), r.getFecha().toString()));
		}
		HttpEntity<List<InfoReserva>> info = new HttpEntity <> (infoReservas);
		ResponseEntity<String> result = restTemplate.postForEntity(url, info, String.class);
		
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
	
	@GetMapping("/redireccion_perfil/{id}")
	public String mostrarPerfil(Model model, HttpSession sesion, HttpServletRequest request, @PathVariable long id)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		
		if(id != 0)
		{
			Reserva res = servicioReservas.getReservaById(id);
			RestTemplate restTemplate = new RestTemplate();

			String url = "http://localhost:8080/email/enviar";
			String asunto = "Reserva '" + res.getNombreReserva() + "' realizada";
			String cuerpo = "¡Gracias por reservar en SportCenterURJC! \n\nLa informacion sobre tu reserva es la siguiente: \n\nRealizada para el centro: " + res.getCentro().getCampus() + "\nActividad reservada: " + res.getActividadRes().getNombreActividad() + "\nFecha reservada: " + res.getFecha().toString() + "\n\n¡Muchas gracias por su apoyo!";
			HttpEntity<InfoCorreo> info = new HttpEntity <> (new InfoCorreo("sportcenterurjc@gmail.com",usuarioActual.getCorreo(),asunto,cuerpo));
			ResponseEntity<String> result = restTemplate.postForEntity(url, info, String.class);
			
			model.addAttribute("usu", usuarioActual);
			model.addAttribute("res", reservas);
			
			return "mostrar_perfil";
		}
		else 
		{	
			model.addAttribute("campus", servicioCentroActual.getCentroActual().getCampus());
			return "actividadReservada";
		}
	}
	
	@PostMapping("/perfil/realizarReserva")
	public void solicitarReservapsot(Model model,Reserva res, @RequestParam String nombreActividad, HttpSession sesion,  HttpServletResponse response) throws IOException
	{
		CentroDeportivo centroActual = servicioCentroActual.getCentroActual();
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		long id = 0;
		
		List<Actividad> actCentro = centroActual.getActividades();
		for(Actividad a : actCentro)
		{
			if(a.getNombreActividad().equalsIgnoreCase(nombreActividad))
			{
				id = a.getId();
			}
		}
		Actividad act = servicioActividades.getActividadById(id);
		
		RestTemplate restTemplate = new RestTemplate();
		Boolean hayLibre = false;
		
		long idRes = 0;
		int numPistas = act.getPistas().size();
		boolean encontrada = false;
		int n = 0;
		
		while(n < numPistas && !encontrada)
		{
			PistaDeportiva p = act.getPistas().get(n);
			String url = "http://localhost:8080/pistas/comprobarPista/" + p.getId();
			hayLibre = restTemplate.getForObject(url, Boolean.class);
			
			if(hayLibre == true)
			{
				p.setOcupado();
				List<Reserva> reservas = usuarioActual.getReservas();
				
				res.setUsuario(usuarioActual);
				res.setActividadRes(act);
				res.setCentro(centroActual);
				reservas.add(res);
				servicioReservas.guardarReserva(res);
				
				idRes = res.getId();
				encontrada = true;
			}
			n++;
		}
		response.sendRedirect("/redireccion_perfil/" + idRes);
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
