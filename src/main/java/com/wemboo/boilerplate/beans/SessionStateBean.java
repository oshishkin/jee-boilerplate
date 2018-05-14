/**
 *  Demonstrates typical Managed session scoped bean usage
 */
package com.wemboo.boilerplate.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.itextpdf.text.DocumentException;
import com.wemboo.boilerplate.db.SampleEntity;
import com.wemboo.boilerplate.db.SampleRepository;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named("sampleStateBean")
public class SessionStateBean implements Serializable{

    public static final String GREETING_TEXT = "Hello World!";

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    private String greeting;

    @Getter
    private StreamedContent media;

    @Inject
    private PdfGenerator pdfGenerator;

    @Inject
    private SampleRepository SampleRepository;

    @Inject
    private transient Logger log;    

    private List<SampleEntity> result;

    @PostConstruct
    public void init() throws DocumentException, IOException {
        log.debug("Initialize session scoped managed bean");
        greeting = GREETING_TEXT;

        byte[] document = pdfGenerator.generate(false);
        media = new DefaultStreamedContent(new ByteArrayInputStream(document), "application/pdf");  

        username = "";
        password = "";
    }

    public List<SampleEntity> getResult() {
        log.debug("Get data");
        result = SampleRepository.findAll();      
        return result;
    }
}
