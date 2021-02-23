package es.urjc.etsii.dad.helloworldweb;

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
	public String registrarse(Model model, @RequestParam String userName, String userPassword, String userMail)
	{
		model.addAttribute("nombre", userName);
		model.addAttribute("contrasenya", userPassword);
		model.addAttribute("correo", userMail);
		return "registrarse_template";
	}
	
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model, @RequestParam String userName, String userPassword)
	{
		model.addAttribute("nombre", userName);
		model.addAttribute("contrasenya", userPassword);
		return "iniciarSesion_template";
	}
}
