/**
 *  Demonstrates typical Session scope bean usage
 */
package com.wemboo.boilerplate.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import com.wemboo.boilerplate.db.SampleEntity;
import com.wemboo.boilerplate.db.SampleRepository;
import org.slf4j.Logger;

@SessionScoped
@ManagedBean(name="sampleStateBean")
public class SessionStateBean implements Serializable{

    public static final String GREETING_TEXT = "Hello World!";
    private String greeting;

    private List<SampleEntity> result;

    @Inject
    private SampleRepository SampleRepository;

    @Inject
    private Logger log;    

    @PostConstruct
    public void init() {
        log.debug("Initialize bean");
        greeting = GREETING_TEXT;
        result = SampleRepository.findAll();      
    }

    public String getGreeting() {
        return greeting;
    }

    public List<SampleEntity> getResult() {
        return result;
    }

}