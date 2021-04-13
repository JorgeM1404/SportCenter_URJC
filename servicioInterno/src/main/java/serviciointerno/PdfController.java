package serviciointerno;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/pdf")
public class PdfController {
	@Autowired
	private PdfService generadorPdf;
	
	@PostMapping("/enviar")
	@ResponseBody
	public boolean descargarPdf(@RequestBody List<InfoReserva> reservas) throws FileNotFoundException, DocumentException
	{
		generadorPdf.generarPdf(reservas);
		return true;
	}
}
