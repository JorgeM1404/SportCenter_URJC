package aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import entidades.Usuario;

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
}
