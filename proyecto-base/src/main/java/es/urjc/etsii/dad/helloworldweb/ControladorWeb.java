package es.urjc.etsii.dad.helloworldweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorWeb 
{
	/*@GetMapping("/hola-mundo")
	public String holaMundo()
	{
		return "hola_mundo.html";
	}*/
	
	@GetMapping("/")
	public String saludo()
	{
		return "saludo.html";
	}
	
	@RequestMapping("/hola")
	public String hola(Model model, @RequestParam String userName)
	{
		model.addAttribute("nombre", userName);
		return "hola_template";
	}
}
