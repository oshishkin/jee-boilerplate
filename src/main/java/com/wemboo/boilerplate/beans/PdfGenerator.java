package com.wemboo.boilerplate.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;

@Stateless
@LocalBean
public class PdfGenerator {

    @Inject
    private Logger log;

    public byte[] generate (Boolean servlet) throws DocumentException, IOException {

        log.debug("Pdf generation started from "+(servlet ? "Servlet" : "Bean"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();;
        Document document = new Document();;
        /* Basic PDF Creation inside servlet */
        PdfWriter.getInstance(document, out);
        document.open();
        document.add(new Paragraph("Tutorial to Generate PDF using Servlet"));
        document.add(new Paragraph("PDF Created Using "+(servlet ? "Servlet" : "Bean")+", iText Example Works"));
        document.close();
        return out.toByteArray();
    }
}