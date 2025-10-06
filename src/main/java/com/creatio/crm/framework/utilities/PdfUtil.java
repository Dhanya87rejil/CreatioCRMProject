package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfUtil {
	
	public static String getText(String fileName, int startPage, int endPage) {
		
		String text ="";
		
		
		try {
			
			//Read the pdf file from specific location
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Files\\"+fileName);
						
			//Load PDF file into PDDocument class to analyze the PDF file.
			PDDocument document = PDDocument.load(fis);
			
			//Extract the text from PDF file using PDFTextStripper class
			PDFTextStripper pdf = new PDFTextStripper();
			
			//Set the start page and end page
			pdf.setStartPage(startPage);
			pdf.setEndPage(endPage);
			
			//Get the text from the PDF file
			text = pdf.getText(document);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return text;
		
	}

}