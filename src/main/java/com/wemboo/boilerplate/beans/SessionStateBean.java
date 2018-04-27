/**
 *  Demonstrates typical Managed session scoped bean usage
 */
package com.wemboo.boilerplate.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.wemboo.boilerplate.db.SampleEntity;
import com.wemboo.boilerplate.db.SampleRepository;
import org.slf4j.Logger;

@SessionScoped
@Named("sampleStateBean")
public class SessionStateBean implements Serializable{

    public static final String GREETING_TEXT = "Hello World!";
    private String greeting;

    private List<SampleEntity> result;

    @Inject
    private SampleRepository SampleRepository;

    @Inject
    private transient Logger log;    

    @PostConstruct
    public void init() {
        log.debug("Initialize session scoped bean");
        greeting = GREETING_TEXT;
    }

    public String getGreeting() {
        return greeting;
    }

    public List<SampleEntity> getResult() {
        log.debug("Get data");
        result = SampleRepository.findAll();      
        return result;
    }

}