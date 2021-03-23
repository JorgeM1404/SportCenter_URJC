package aplicacion;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsuarioController 
{
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	@Autowired
	private ServicioReservas servicioReservas;
	
	private boolean datosIncorrectosReg = false;
	private boolean datosIncorrectosIni = false;
	private boolean usuarioNoExiste = false;
	private boolean usuarioYaExiste = false;
	
	@GetMapping("/")
	public String principal()
	{
		return "inicio";
	}
	
	@GetMapping("/menuPrincipal")
	public String irAmenuPrincipal(Model model, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		model.addAttribute("usu",usuarioActual);
		return "paginaPrincipal";
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
		model.addAttribute("datosIncorrectos", datosIncorrectosReg);
		model.addAttribute("usuarioYaExiste", usuarioYaExiste);
		datosIncorrectosReg = false;
		usuarioYaExiste = false;
		
		return "registrarse";
	}
	@PostMapping("/usuario/nuevo")
	public String registrarse(Model model, Usuario usuario, HttpSession sesion)
	{
		datosIncorrectosReg = false;
		usuarioYaExiste = false;
		if(usuario.getNombre().trim().equals("") || usuario.getPasswordHash().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectosReg = true;
			model.addAttribute("datosIncorrectosReg", datosIncorrectosReg);
			
			return "error";
		}
		else
		{
			if(!servicioUsuarios.existeUsuario(usuario))
			{
				servicioUsuarios.guardarUsuario(usuario);
				//Usuario usuarioActual = usuario;
				model.addAttribute("usu", usuario);
				sesion.setAttribute("usuarioActual", usuario);
				return "paginaPrincipal";
			}
			else
			{
				usuarioYaExiste = true;
				model.addAttribute("usuarioYaExiste", usuarioYaExiste);
				
				return "error";
			}
		}
	}
	
	@GetMapping("/usuario/acceso")
	public String acceder(Model model)
	{
		model.addAttribute("datosIncorrectosIni", datosIncorrectosIni);
		model.addAttribute("usuarioNoExiste", usuarioNoExiste);
		usuarioNoExiste = false;
		datosIncorrectosIni = false;
		
		return "iniciarSesion";
	}
	@PostMapping("/usuario/acceso")
	public String iniciarSesion(Model model,Usuario usuario, HttpSession sesion)
	{
		usuarioNoExiste = false;
		datosIncorrectosIni = false;
		if(usuario.getNombre().trim().equals("") || usuario.getPasswordHash().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectosIni = true;
			model.addAttribute("datosIncorrectosIni", datosIncorrectosIni);
			
			return "error";
		}
		else
		{
			Usuario usu = servicioUsuarios.getUsuarioByCampos(usuario.getNombre(), usuario.getPasswordHash(), usuario.getCorreo());
			if(servicioUsuarios.existeUsuario(usu))
			{
				//usuarioActual = usu;
				model.addAttribute("usu", usu);
				sesion.setAttribute("usuarioActual", usu);
				return "paginaPrincipal";
			}
			else
			{
				usuarioNoExiste = true;
				model.addAttribute("usuarioNoExiste", usuarioNoExiste);
				
				return "error";
			}
		}
	}
	
	// prueba con mappings nuevos del login
	
	/*
	 
	 
	 @PostMapping("/usuario/acceso")
	public void iniciarSesion(Model model,Usuario usuario, HttpSession sesion, HttpServletResponse response) throws IOException
	{
		usuarioNoExiste = false;
		datosIncorrectosIni = false;
		if(usuario.getNombre().trim().equals("") || usuario.getClave().trim().equals("") || usuario.getCorreo().trim().equals(""))
		{
			datosIncorrectosIni = true;
			model.addAttribute("datosIncorrectosIni", datosIncorrectosIni);
			
			response.sendRedirect("/error");
		}
		else
		{
			Usuario usu = servicioUsuarios.getUsuarioByCampos(usuario.getNombre(), usuario.getClave(), usuario.getCorreo());
			if(servicioUsuarios.existeUsuario(usu))
			{
				//usuarioActual = usu;
				//model.addAttribute("usu", usu);
				sesion.setAttribute("usuarioActual", usu);
				//model.addAttribute("usu", usu);
				response.sendRedirect("/loginCorrecto");
			}
			else
			{
				//usuarioNoExiste = true;
				//model.addAttribute("usuarioNoExiste", usuarioNoExiste);
				
				response.sendRedirect("/error");
			}
		}
	}
	
	@GetMapping("/error")
	public String errorLogin(Model model)
	{
		usuarioNoExiste = true;
		model.addAttribute("usuarioNoExiste", usuarioNoExiste);
		return "error";
	}
	
	@GetMapping("/loginCorrecto")
	public String correctoLogin(Model model, HttpSession sesion)
	{
		Usuario usu = (Usuario) sesion.getAttribute("usuarioActual");
		model.addAttribute("usu", usu);
		return "paginaPrincipal";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "inicio";
	}
	
	  
	 
	 */
	
	@GetMapping("/perfil")
	public String miPerfil(Model model, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
	
	@GetMapping("/perfil/{id}")
	public String verReserva(Model model, @PathVariable long id)
	{
		Reserva res = servicioReservas.getReservaById(id);
		Actividad act = res.getActividadRes();
		CentroDeportivo centro = res.getCentro();
		
		model.addAttribute("res",res);
		model.addAttribute("act",act);
		model.addAttribute("centro",centro);
		
		return "reserva";
	}
}
