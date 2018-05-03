package com.wemboo.boilerplate.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.wemboo.boilerplate.beans.PdfGenerator;

import org.slf4j.Logger;


@WebServlet("sampleservlet")
public class SampleServlet extends HttpServlet {

    @Inject
    private Logger log;

    @Inject
    private PdfGenerator pdfGenerator;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        processRequest(req, resp);
    }


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

            log.debug("Get PDF from servlet");

            //Set content type to application / pdf
            //browser will open the document only if this is set
            resp.setContentType("application/pdf");

            //Get the output stream for writing PDF object        
            OutputStream out=resp.getOutputStream();
			try {
				byte[] document = pdfGenerator.generate(true);
                out.write(document);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            }
            finally {
                out.close();
            }
    }
}