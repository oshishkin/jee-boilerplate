/**
 *  Demonstrates typical Backing Request scoped bean usage
 */
package com.wemboo.boilerplate.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import com.wemboo.boilerplate.db.SampleEntity;
import com.wemboo.boilerplate.db.SampleRepository;
import org.slf4j.Logger;

@RequestScoped
@Named("sampleRequestBean")
public class RequestStateBean implements Serializable{

    private String name;

    @Inject
    private SampleRepository SampleRepository;

    @Inject
    private transient Logger log;    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log.debug("Setter invoked with name: "+name);
        this.name = name;
    }


    public void buttonAction(ActionEvent actionEvent) {
        log.debug("Action listener invoked with name: "+this.name);

        SampleEntity sampleEntity = new SampleEntity();
        
        sampleEntity.setName(name);
        SampleRepository.save(sampleEntity);

        addMessage("Added name: "+this.name);
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    

}