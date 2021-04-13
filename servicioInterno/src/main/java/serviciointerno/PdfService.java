package serviciointerno;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

	public void generarPdf(List<InfoReserva> reservas) throws FileNotFoundException, DocumentException
	{
		Document documento = new Document();
		FileOutputStream ficheroPDF = new FileOutputStream("Mis Reservas.pdf");
		PdfWriter.getInstance(documento, ficheroPDF);
		
		documento.open();
		Paragraph titulo = new Paragraph("MIS RESERVAS\n\n", FontFactory.getFont("arial",22,Font.BOLD, BaseColor.RED));
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(4);
		tabla.addCell("NOMBRE");
		tabla.addCell("CAMPUS");
		tabla.addCell("ACTIVIDAD");
		tabla.addCell("FECHA");
		for(InfoReserva r : reservas)
		{
			tabla.addCell(r.getNombre());
			tabla.addCell(r.getCampus());
			tabla.addCell(r.getActividad());
			tabla.addCell(r.getFecha());
		}
		documento.add(tabla);
		documento.close();
	}
}
