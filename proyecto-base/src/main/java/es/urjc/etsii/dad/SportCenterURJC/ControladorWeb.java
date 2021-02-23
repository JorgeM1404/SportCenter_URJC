package es.urjc.etsii.dad.SportCenterURJC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorWeb 
{	
	@GetMapping("/")
	public String principal()
	{
		return "pagPrincipal.html";
	}
	
	@RequestMapping("/registrarse")
	public String registrarse(Model model, @RequestParam Usuario usuario)
	{
		model.addAttribute("usuario", usuario);
		return "registrarse_template";
	}
	
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model, @RequestParam Usuario usuario)
	{
		model.addAttribute("usuario", usuario);
		return "iniciarSesion_template";
	}
}
