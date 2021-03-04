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
	
	private Usuario usuarioActual;
	private CentroDeportivo centroActual;
	
	private boolean datosIncorrectos = false;
	private boolean usuarioNoExiste = false;
	private boolean usuarioYaExiste = false;

	@GetMapping("/")
	public String principal()
	{
		return "inicio";
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
		model.addAttribute("datosIncorrectos", datosIncorrectos);
		model.addAttribute("usuarioYaExiste", usuarioYaExiste);
		datosIncorrectos = false;
		usuarioYaExiste = false;
		
		return "registrarse";
	}
	
	@PostMapping("/usuario/nuevo")
	public String registrarse(Model model, Usuario usuario)
	{
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectos = true;
			model.addAttribute("datosIncorrectos", datosIncorrectos);
			datosIncorrectos = false;
			
			return "registrarse";
		}
		else
		{
			Usuario usu = servicioUsuarios.getUsuario(usuario.getNombre());
			if(!servicioUsuarios.existeUsuario(usu))
			{
				servicioUsuarios.guardarUsuario(usuario);
				usuarioActual = usuario;
				model.addAttribute("usu", usuarioActual);
				
				return "seleccion_campus";
			}
			else
			{
				usuarioYaExiste = true;
				model.addAttribute("usuarioYaExiste", usuarioYaExiste);
				usuarioYaExiste = false;
				
				return "yaExisteUsuario";
			}
		}
	}
	
	@GetMapping("/usuario/acceso")
	public String acceder(Model model)
	{
		model.addAttribute("datosIncorrectos", datosIncorrectos);
		model.addAttribute("usuarioNoExiste", usuarioNoExiste);
		usuarioNoExiste = false;
		datosIncorrectos = false;
		
		return "iniciarSesion";
	}
	@PostMapping("/usuario/acceso")
	public String iniciarSesion(Model model,Usuario usuario)
	{
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectos = true;
			model.addAttribute("datosIncorrectos", datosIncorrectos);
			datosIncorrectos = false;
			
			return "iniciarSesion";
		}
		else
		{
			Usuario usu = servicioUsuarios.getUsuario(usuario.getNombre());
			if(servicioUsuarios.existeUsuario(usu))
			{
				usuarioActual = usu;
				model.addAttribute("usu", usuarioActual);
				return "seleccion_campus";
			}
			else
			{
				usuarioNoExiste = true;
				model.addAttribute("usuarioNoExiste", usuarioNoExiste);
				usuarioNoExiste = false;
				
				return "noExisteUsuario";
			}
		}
	}
	
	/*@GetMapping("/campus/actividades/{num}")
	public String SeleccionarActividad(Model model, @PathVariable int num)
	{
		CentroDeportivo centro = servicioCentros.findById(0);
		model.addAttribute("centro", centro);
		
		return "actividad";
	}*/
	
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
			case "Mostoles": plantilla = "campus1"; break;				
			case "Alcorcón": plantilla = "campus2"; break;
			case "Fuenlabrada": plantilla = "campus3"; break;
			case "Aranjuez": plantilla = "campus4"; break;
			case "Vicálvaro": plantilla = "campus5"; break;
		}	
		return plantilla;
	}
	
	@GetMapping("/campus")
	public String menuCampus()
	{
		return "seleccion_campus";
	}
	
	@GetMapping("/menuPrincipal")
	public String irAmenuPrincipal(Model model)
	{
		model.addAttribute("usu",usuarioActual);
		return "seleccion_campus";
	}
	
	@GetMapping("/perfil")
	public String miPerfil(Model model)
	{
		model.addAttribute("usu", usuarioActual);
		return "mostrar_perfil";
	}
}
