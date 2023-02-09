package com.codeinteracts.moviereview.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class GenPDF {
	
	
	@RequestMapping("/idpdf")
	public String generatePDFUsingIpdf() throws IOException, DocumentException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("hello-itext.pdf"));
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.COURIER, 20);
		
		String value = "Hello World!!!!!!";
		Chunk chunk = new Chunk(value, font);
		chunk.append(" This is append operation.");
		document.add(chunk);
		
		Paragraph prg = new Paragraph("\n \n");
		document.add(prg);
		
		PdfPTable table = new PdfPTable(2);
		table.addCell("Cell 1");
		table.addCell("Cell 2");
		
		document.add(table);
		
		document.close();
		return "Successful";
	}
	
	@RequestMapping("/pdf")
	public String generatePDF() throws IOException {
		//Take user input
		
		
		//Process input
		
		
		//Add user input in pdf
		
		
		//Generate pdf named, username_cv.pdf
		
		
		
		PDDocument pdDocument = new PDDocument();
		PDPage page = new PDPage();
		
		pdDocument.addPage(page);
		PDPageContentStream contStream = new PDPageContentStream(pdDocument, page);
		
		contStream.setFont(PDType1Font.TIMES_ROMAN, 50);
		contStream.beginText();
		String value = "Hello World!!!!!!";
		
		contStream.showText(value);
		contStream.endText();

		contStream.close();
		pdDocument.save("demo.pdf");
		pdDocument.close();
		
		return "Successful";
	}
	
	@RequestMapping("/get/pdf")
	ResponseEntity<StreamingResponseBody> getPDF() throws IOException {
		
		PDDocument document = PDDocument.load(new File("demo.pdf"));
		
		// Save the document to a byte array
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		document.save(outputStream);

		byte[] pdfContent = outputStream.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "file.pdf");

		StreamingResponseBody stream = out -> {
			out.write(pdfContent);
		};

		return new ResponseEntity<>(stream, headers, HttpStatus.OK);
		
	}
	
}
