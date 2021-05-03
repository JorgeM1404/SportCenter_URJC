package aplicacion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@CacheConfig(cacheNames="cacheURJC")
@Controller
public class UsuarioController 
{
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	@Autowired
	private ServicioReservas servicioReservas;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	public String irAmenuPrincipal(Model model, HttpSession sesion, HttpServletRequest request)
	{
		String nombre = request.getUserPrincipal().getName();
		Usuario usuarioActual = servicioUsuarios.getUsuarioByNombre(nombre);
		
		sesion.setAttribute("usuarioActual", usuarioActual);
		model.addAttribute("nombre", usuarioActual.getNombre());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		return "paginaPrincipal";
	}
	
	@GetMapping("/registrarse")
	public String registrarse(Model model, HttpServletRequest request)
	{
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());
	    
		return "registrarse";
	}	
	
	/*@GetMapping("/usuario/nuevo")
	public String registrarse1(Model model)
	{
		model.addAttribute("datosIncorrectos", datosIncorrectosReg);
		model.addAttribute("usuarioYaExiste", usuarioYaExiste);
		datosIncorrectosReg = false;
		usuarioYaExiste = false;
		
		return "registrarse";
	}*/
	@CacheEvict
	@PostMapping("/registrarse")//@PostMapping("/usuario/nuevo")
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
				usuario.getRoles().add("USER");
				usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));				
				servicioUsuarios.guardarUsuario(usuario);

				//model.addAttribute("nombre", usuario.getNombre());
				//sesion.setAttribute("usuarioActual", usuario);
				
				return "confirmacionRegistro";
			}
			else
			{
				usuarioYaExiste = true;
				model.addAttribute("usuarioYaExiste", usuarioYaExiste);
				
				return "error";
			}
		}
	}
	
	@GetMapping("/login")
	public String RealizarLogin(Model model, HttpServletRequest request)
	{
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken()); 
	    
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String falloLogin(Model model)
	{
		model.addAttribute("datosIncorrectosIni", true);
		return "error";
	}
	
	@Cacheable
	@GetMapping("/perfil")
	public String miPerfil(Model model, HttpSession sesion)
	{
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		List<Reserva> reservas = usuarioActual.getReservas();
		model.addAttribute("usu", usuarioActual);
		model.addAttribute("res", reservas);
		return "mostrar_perfil";
	}
	
	@Cacheable
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
