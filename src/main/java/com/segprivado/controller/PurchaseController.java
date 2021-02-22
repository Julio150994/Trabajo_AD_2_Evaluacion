package com.segprivado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lowagie.text.DocumentException;
import com.segprivado.configuration.InvoicePDFExporter;
import com.segprivado.model.PurchaseModel;
import com.segprivado.service.impl.PurchaseImpl;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/purchases")
public class PurchaseController {
	private static final Log LOG_PURCHASE = LogFactory.getLog(PurchaseController.class);
	
	@Autowired
	@Qualifier("purchaseImpl")
	private PurchaseImpl purchases;
	
	@Autowired
	private HttpSession httpUsers;
	
	
	@PostMapping("/addPurchase")
	public String addPurchase(@RequestParam(name="id",required=false) Integer id) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Sesiones correspondientes al usuario actual logueado
		Integer numSessions = (Integer) httpUsers.getAttribute("cont");
		if(numSessions != null)
			httpUsers.setAttribute("cont",numSessions+1);
		else
			httpUsers.setAttribute("cont",1);
		
		LOG_PURCHASE.info("Number visits of "+user.getUsername()+": "+numSessions);
		return "";
	}
	
	/*-------Método para exportar las compras en un PDF-----------*/
	@GetMapping("/exportInvoice/pdf")
	public void exportPatientsPDF(HttpServletResponse httpRes) {	 
		try {
			 httpRes.setContentType("application/pdf");//para que nuestra aplicación nos acepte este tipo de ficheros
		         
		     String key = "Content-Disposition";
		     String value = "attachment; filename=purchases.pdf";
		     httpRes.setHeader(key,value);
		         
		     List<PurchaseModel> listPurchase = purchases.listAllPurchases();
		         
		     InvoicePDFExporter pdfInvoice = new InvoicePDFExporter(listPurchase);
		     pdfInvoice.exportInvoicePDF(httpRes);
		}
		catch(DocumentException e) {
			 System.out.println(e.getMessage());
		}    
	}
}
