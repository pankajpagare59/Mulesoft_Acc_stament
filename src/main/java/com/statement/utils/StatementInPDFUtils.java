package com.statement.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;





public class StatementInPDFUtils implements Callable{
	
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Content-Disposition", "inline; image = statement.pdf");
		Document document = new Document();
		
		final String FILE_NAME = "D:\\AnypointStudio-for-win-64bit-6.5.0-201806071907\\workspace\\accountstatement\\src\\main\\java\\com\\statement\\utils\\statment.pdf";
		
		MuleMessage message = eventContext.getMessage();
		System.out.println("java class: "+ message.getPayloadAsString());
		String response = message.getPayloadAsString();
		JSONArray jArray = new JSONArray(response);
		
		JSONObject jsonobjectHeader = jArray.getJSONObject(0);
		System.out.println("jsonobjectHeader :" + jsonobjectHeader.toString());
		try {
				
			
		if(jsonobjectHeader!=null){

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(60);
		com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("AccountId", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		
		hcell = new PdfPCell(new Phrase("StatementId", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("StatementDate", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Description", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
		
		
		PdfWriter.getInstance(document,out);
		document.open();
		addContent(document,jsonobjectHeader);
		document.add(table);
		document.close();
		}
		
		}
		catch (DocumentException ex) {
			throw new Exception(ex.getMessage());
		}
				
		
		return ResponseEntity .ok() .headers(headers).body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
	}
	
private static void addContent(Document document,JSONObject jsonobjectHeader) throws DocumentException, JSONException {
        
        // first parameter is the number of the chapter
        Chapter catPart = new Chapter(0);
        Paragraph subPara = new Paragraph("Customer Information");
        Section subCatPart = catPart.addSection(subPara);
        
        subCatPart.add(new Paragraph("Name: Pankaj"));
        subCatPart.add(new Paragraph("AccountId: "+ jsonobjectHeader.getString("AccountId")));
        subCatPart.add(new Paragraph("StatementId: "+ jsonobjectHeader.getString("StatementId")));
        subCatPart.add(new Paragraph("StatementDate: "+ jsonobjectHeader.getString("StatementDate")));
        subCatPart.add(new Paragraph("Description: "+ jsonobjectHeader.getString("Description")));
      
        
        subCatPart.add(new Paragraph(""));
       
        // now add all this to the document
        document.add(subCatPart);

    }

}

