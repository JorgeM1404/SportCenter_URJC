package aplicacion;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import entidades.*;

@Controller
public class ControladorWeb 
{		
	@Autowired
	private ServicioUsuarios servicio;
	
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
		if(!servicio.findAll().contains(usuario))
		{
			servicio.nuevoUsuario(usuario);
			model.addAttribute("usu",usuario);
			return "seleccion_campus";
		}
		else return "yaExisteUsuario";
	}
	
	@PostMapping("/usuario/acceso")
	public String iniciarSesion(Model model, Usuario usuario)
	{
		if(servicio.findAll().contains(usuario))
		{
			model.addAttribute("usu", usuario);
			return "seleccion_campus";
		}
		else return "noExisteUsuario";
	}
	
	@GetMapping("/campus/{num}")
	public String SeleccionarCampus(Model model, @PathVariable int num)
	{
		
		CentroDeportivo centro = null;
		switch (num) {
		case 1: centro = new CentroDeportivo ("Móstoles");
				break;
		case 2: centro = new CentroDeportivo ("Alcorcón");
				break;
		case 3: centro = new CentroDeportivo ("Fuenlabrada");
				break;
		case 4: centro = new CentroDeportivo ("Aranjuez");
				break;
		case 5: centro = new CentroDeportivo ("Vicálvaro");
				break;
		}
		model.addAttribute("centro", centro );
		return "campus";
	}
	
	@GetMapping("/campus")
	public String menuCampus()
	{
		return "seleccion_campus";
	}
	
	@GetMapping("/perfil")
	public String miPerfil(Model model, @RequestParam Usuario usuario)
	{
		model.addAttribute("usu", usuario);
		return "iniciarSesion_template";
	}
}
