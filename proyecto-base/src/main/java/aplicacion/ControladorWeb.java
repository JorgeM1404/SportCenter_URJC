package aplicacion;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import entidades.*;

@Controller
public class ControladorWeb 
{		
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	/*@Autowired
	private ServicioCentroDeportivo servicioCentros;*/
	
	private Usuario usuarioActual;
	
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
		if(!servicioUsuarios.findAll().contains(usuario))
		{
			servicioUsuarios.nuevoUsuario(usuario);
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
			if(servicioUsuarios.findAll().contains(usuario))
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
	
	@GetMapping("/campus/{num}")
	public String SeleccionarCampus(Model model, @PathVariable int num)
	{	
		CentroDeportivo centro = null;
		
		switch (num) 
		{
		case 1: centro = new CentroDeportivo ("Móstoles");//centro = centro1;
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
		model.addAttribute("centro", centro);
		if (num == 1)
		 return "campus1";
		if (num == 2)
			 return "campus2";
		if (num == 3)
			 return "campus3";
		if (num == 4)
			 return "campus4";
		else
			 return "campus5";
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
