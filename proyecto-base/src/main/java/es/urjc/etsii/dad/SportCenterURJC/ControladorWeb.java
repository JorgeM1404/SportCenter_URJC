package es.urjc.etsii.dad.SportCenterURJC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorWeb 
{		
	
	@GetMapping("/")
	public String inicio()
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
	
	// Controlador anterior
	
	/*@GetMapping("/")
	public String principal()
	{
		return "index";
	}
	
	@PostMapping("/usuario/nuevo")
	public String registrarse(Model model, Usuario usuario)
	{
		model.addAttribute("usu",usuario);
		return "registrarse_template";
	}
	
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model, @RequestParam Usuario usuario)
	{		
		model.addAttribute("usuario", usuario);
		return "iniciarSesion_template";
	}*/
}
