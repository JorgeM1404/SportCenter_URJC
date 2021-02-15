package es.urjc.etsii.dad.helloworldweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController 
{
	@GetMapping("/hello")
	public String helloworld()
	{
		return "hello-world.html";
	}
	
	@GetMapping("/hello-jorge")
	public String helloJorge(Model model)
	{
		model.addAttribute("name", "Jorge");
		return "hello";
	}
}
