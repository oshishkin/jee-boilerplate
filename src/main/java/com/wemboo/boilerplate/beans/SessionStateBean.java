/**
 *  Demonstrates typical Session scope bean usage
 */
package com.wemboo.boilerplate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class SessionStateBean implements Serializable{

    public static final String GREETING_TEXT = "Hello World!";
    private String greeting;


    @PostConstruct
    public void init() {
      greeting = GREETING_TEXT;
    }

    public String getGreeting() {
        return greeting;
    }

}