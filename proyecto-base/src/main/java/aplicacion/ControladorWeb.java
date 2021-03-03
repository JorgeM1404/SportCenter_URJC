package aplicacion;

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
	
	private boolean errorNombre = false;
	private boolean errorClave = false;
	private boolean faltanDatos = false;
	
	//private CentroDeportivo centro1;
	
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
	
	@PostMapping("/usuario/nuevo")
	public String registrarse(Model model, Usuario usuario)
	{
		if(!servicioUsuarios.getUsuarios().contains(usuario))
		{
			servicioUsuarios.guardarUsuario(usuario);
			model.addAttribute("usu",usuario);
			return "seleccion_campus";
		}
		else return "yaExisteUsuario";
	}
	
	@GetMapping("/usuario/acceso")
	public String acceder(Model model)
	{
		model.addAttribute("errorNombre", errorNombre);
		model.addAttribute("errorClave", errorClave);
		
		return "iniciarSesion";
	}
	@PostMapping("/usuario/acceso")
	public String iniciarSesion(Model model,Usuario usuario)
	{
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals(""))
		{
			faltanDatos = true;
			model.addAttribute("faltanDatos", faltanDatos);
			faltanDatos = false;
			
			return "iniciarSesion";
		}
		else
		{
			if(servicioUsuarios.getUsuarios().contains(usuario))
			{
				usuarioActual = usuario;
				model.addAttribute("usu", usuario);
				return "seleccion_campus";
			}
			else
			{
				errorNombre = true;
				model.addAttribute("errorNombre",errorNombre);
				errorNombre = false;
				
				return "iniciarSesion";
			}
		}
		
		/*
		if(servicio.findAll().contains(usuario))
		{
			model.addAttribute("usu", usuario);
			return "seleccion_campus";
		}
		else return "noExisteUsuario";*/
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
			case "Alcorcón": plantilla = "campus1"; break;
			case "Fuenlabrada": plantilla = "campus1"; break;
			case "Aranjuez": plantilla = "campus1"; break;
			case "Vicálvaro": plantilla = "campus1"; break;
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
