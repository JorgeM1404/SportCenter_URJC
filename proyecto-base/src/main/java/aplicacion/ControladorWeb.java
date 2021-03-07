package aplicacion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorWeb 
{		
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	private ServicioCentros servicioCentros;
	
	@Autowired
	private ServicioActividades servicioActividades;
	
	@Autowired
	private ServicioReservas servicioReservas;
	
	private Usuario usuarioActual;
	private CentroDeportivo centroActual;
	
	private boolean datosIncorrectosReg = false;
	private boolean datosIncorrectosIni = false;
	private boolean usuarioNoExiste = false;
	private boolean usuarioYaExiste = false;

	@GetMapping("/")
	public String principal()
	{
		return "inicio";
	}
	
	@GetMapping("/menuPrincipal")
	public String irAmenuPrincipal(Model model)
	{
		model.addAttribute("usu",usuarioActual);
		return "paginaPrincipal";
	}
	
	@GetMapping("/registrarse")
	public String registrarse()
	{
		return "registrarse";
	}
	
	@GetMapping("/iniciarSesion")
	public String iniciarSesion()
	{
		return "iniciarSesion";
	}
	
	@GetMapping("/usuario/nuevo")
	public String registrarse1(Model model)
	{
		model.addAttribute("datosIncorrectos", datosIncorrectosReg);
		model.addAttribute("usuarioYaExiste", usuarioYaExiste);
		datosIncorrectosReg = false;
		usuarioYaExiste = false;
		
		return "registrarse";
	}
	@PostMapping("/usuario/nuevo")
	public String registrarse(Model model, Usuario usuario)
	{
		datosIncorrectosReg = false;
		usuarioYaExiste = false;
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectosReg = true;
			model.addAttribute("datosIncorrectosReg", datosIncorrectosReg);
			
			return "error";
		}
		else
		{
			if(!servicioUsuarios.existeUsuario(usuario))
			{
				servicioUsuarios.guardarUsuario(usuario);
				usuarioActual = usuario;
				model.addAttribute("usu", usuarioActual);
				
				return "paginaPrincipal";
			}
			else
			{
				usuarioYaExiste = true;
				model.addAttribute("usuarioYaExiste", usuarioYaExiste);
				
				return "error";
			}
		}
	}
	
	@GetMapping("/usuario/acceso")
	public String acceder(Model model)
	{
		model.addAttribute("datosIncorrectosIni", datosIncorrectosIni);
		model.addAttribute("usuarioNoExiste", usuarioNoExiste);
		usuarioNoExiste = false;
		datosIncorrectosIni = false;
		
		return "iniciarSesion";
	}
	@PostMapping("/usuario/acceso")
	public String iniciarSesion(Model model,Usuario usuario)
	{
		usuarioNoExiste = false;
		datosIncorrectosIni = false;
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectosIni = true;
			model.addAttribute("datosIncorrectosIni", datosIncorrectosIni);
			
			return "error";
		}
		else
		{
			Usuario usu = servicioUsuarios.getUsuarioByCampos(usuario.getNombre(), usuario.getClave(), usuario.getCorreo());
			if(servicioUsuarios.existeUsuario(usu))
			{
				usuarioActual = usu;
				model.addAttribute("usu", usuarioActual);
				return "paginaPrincipal";
			}
			else
			{
				usuarioNoExiste = true;
				model.addAttribute("usuarioNoExiste", usuarioNoExiste);
				
				return "error";
			}
		}
	}
	
	@GetMapping("/campus")
	public String menuCampus(Model model)
	{
		return "paginaPrincipal";
	}
	
	@GetMapping("/campus/{campus}")
	public String SeleccionarCampus(Model model, @PathVariable String campus)
	{	
		String plantilla = "";
		centroActual = servicioCentros.getCentro(campus);//.getCentroByCampus(campus);
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
	
	@GetMapping("/campus/actividades")
	public String menuActividades(Model model)
	{
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
	
	@GetMapping("/campus/actividades/{nombre}")
	public String SeleccionarActividad(Model model, @PathVariable String nombre)
	{
		Actividad act = servicioActividades.getActividad(nombre);
		List<CentroDeportivo> centros = act.getCentrosSalvo(centroActual);
		List<PistaDeportiva> pistas = act.getPistas();
		
		model.addAttribute("pistas", pistas);
		model.addAttribute("centros", centros);
		model.addAttribute("act", act);
		
		return "actividad";
	}
	
	@GetMapping("/perfil/reservaCancelada/{id}")
	public String miPerfil2(Model model, @PathVariable long id)
	{
		List<Reserva> reservas = usuarioActual.getReservas();
		reservas.remove(servicioReservas.getReservaById(id));
		servicioReservas.cancelarReservaById(id);
		
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
	
	@GetMapping("/perfil")
	public String miPerfil(Model model)
	{
		List<Reserva> reservas = usuarioActual.getReservas();
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
	
	@GetMapping("/perfil/{id}")
	public String verReserva(Model model, @PathVariable long id)
	{
		Reserva res = servicioReservas.getReservaById(id);
		Actividad act = res.getActividadRes();
		CentroDeportivo centro = res.getCentro();
		
		model.addAttribute("res",res);
		model.addAttribute("act",act);
		model.addAttribute("centro",centro);
		
		return "reserva";
	}
	
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
	
	@PostMapping("/perfil/realizarReserva")
	public String solicitarReservapsot(Model model,Reserva res, @RequestParam String nombreActividad)
	{
		Actividad act = servicioActividades.getActividad(nombreActividad);
		List<Reserva> reservas = usuarioActual.getReservas();
		
		res.setUsuario(usuarioActual);
		res.setActividadRes(act);
		res.setCentro(centroActual);
		reservas.add(res);
		servicioReservas.guardarReserva(res);
		
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
}
