package com.segprivado.configuration;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.segprivado.model.PurchaseModel;


public class InvoicePDFExporter {
	
	private List<PurchaseModel> listPurchase;
	private Font fontTxt;

	public InvoicePDFExporter(List<PurchaseModel> purchases) {
		this.listPurchase = purchases;
	}
	
	private void headTablePatient(PdfPTable purchase) {
		PdfPCell cellColumn = new PdfPCell();
		cellColumn.setBackgroundColor(Color.BLACK);
		cellColumn.setPadding(5);
		
		fontTxt = FontFactory.getFont(FontFactory.TIMES_BOLD);
		fontTxt.setColor(Color.WHITE);
		
		cellColumn.setPhrase(new Phrase("Id",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Nombre",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Apellidos",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Edad",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Dirección",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Foto",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Username",fontTxt));
		purchase.addCell(cellColumn);
		
		cellColumn.setPhrase(new Phrase("Password",fontTxt));
		purchase.addCell(cellColumn);
	}
	
	private void bodyTablePatient(PdfPTable purchasesPDF) {
		for(PurchaseModel purchase: listPurchase) {
			purchasesPDF.addCell(String.valueOf(purchase.getId()));
			purchasesPDF.addCell(String.valueOf(purchase.getFecha()));
			purchasesPDF.addCell(String.valueOf(purchase.getPrecio()));
			purchasesPDF.addCell(String.valueOf(purchase.getIdPaciente()));
		}
	}
	
	@GetMapping("/exportInvoice/pdf")
	public void exportInvoicePDF(HttpServletResponse httpRes) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc,httpRes.getOutputStream());
			
			doc.open();//para abrir el documento
			fontTxt = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontTxt.setSize(20);
			fontTxt.setColor(Color.ORANGE);
			
			Paragraph paragraph = new Paragraph("<h2>Invoice of segprivado</h2>",fontTxt);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(paragraph);
			
			PdfPTable purchasePDF = new PdfPTable(5);
			purchasePDF.setWidthPercentage(100f);
			purchasePDF.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});// dimensiones del documento PDF
			purchasePDF.setSpacingBefore(10);
	         
			headTablePatient(purchasePDF);//imprimimos la cabecera de la tabla
	        bodyTablePatient(purchasePDF);//imprimimos el cuerpo de la tabla
	         
	        doc.add(purchasePDF);
	        doc.close();
			
		}
		catch(DocumentException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
