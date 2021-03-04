package aplicacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	//private boolean errorNombre = false;
	//private boolean errorClave = false;
	//private boolean faltanDatos = false;
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
			//datosIncorrectos = false;
			
			return "registrarse";
		}
		else
		{
			Optional<Usuario> usu = servicioUsuarios.getUsuarioByAllFields(usuario.getNombre(), usuario.getClave(), usuario.getCorreo());
			if(!usu.isPresent())
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
				//usuarioYaExiste = false;
				
				return "registrarse";
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
			//datosIncorrectos = false;
			
			return "iniciarSesion";
		}
		else
		{
			Optional<Usuario> usu = servicioUsuarios.getUsuarioByAllFields(usuario.getNombre(), usuario.getClave(), usuario.getCorreo());
			if(usu.isPresent())
			{
				usuarioActual = usu.orElseThrow();
				model.addAttribute("usu", usuarioActual);
				return "seleccion_campus";
			}
			else
			{
				usuarioNoExiste = true;
				model.addAttribute("usuarioNoExiste", usuarioNoExiste);
				//usuarioNoExiste = false;
				
				return "iniciarSesion";
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
		centroActual = servicioCentros.getCentroByCampus(campus);
		/*switch (num) 
		{
			case 1: centroActual = servicioCentros.getCentroByCampus("Mostoles"); break;				
			case 2: centroActual = new CentroDeportivo ("Alcorcón"); break;
			case 3: centroActual = new CentroDeportivo ("Fuenlabrada"); break;
			case 4: centroActual = new CentroDeportivo ("Aranjuez"); break;
			case 5: centroActual = new CentroDeportivo ("Vicálvaro"); break;
		}*/
		
		model.addAttribute("centro", centroActual);
		
		switch (campus) 
		{
			case "Mostoles": plantilla = "campus1"; break;				
			case "Alcorcón": plantilla = "campus2"; break;
			case "Fuenlabrada": plantilla = "campus3"; break;
			case "Aranjuez": plantilla = "campus4"; break;
			case "Vicálvaro": plantilla = "campus5"; break;
		}
		
		/*if (campus.equals("Mostoles")) plantilla = "campus1";
		if (campus.equals("Alcorcón")) plantilla =  "campus2";
		if (campus.equals("Fuenlabrada")) plantilla = "campus3";
		if (campus.equals("Aranjuez")) plantilla =  "campus4";
		else plantilla =  "campus5";*/
		
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
	
	/*@GetMapping("/perfil/{id}")
	public String miPerfil(Model model, @PathVariable long id)
	{
		Usuario usuario = servicio.findById(id);
		model.addAttribute("usu", usuario);
		return "iniciarSesion_template";
	}*/
}
