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
    private Logger log;    

    @PostConstruct
    public void init() {
        log.debug("Initialize bean");
        greeting = GREETING_TEXT;
    }

    public String getGreeting() {
        return greeting;
    }

    public List<SampleEntity> getResult() {
        // TODO
        // Use SessionStateBean as ManagedProperty in RequestScopeBean
        // and update result from RequestStateBean's actionListener method
        result = SampleRepository.findAll();      
        return result;
    }

}