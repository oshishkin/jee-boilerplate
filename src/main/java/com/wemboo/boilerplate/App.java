package com.wemboo.boilerplate;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by dima on 8/10/16.
 * All React routes should be configured to index.html page here
 */
@ApplicationPath(App.APIPATH)
public class App extends Application {

    static final String APIPATH = "/";

    public String getGreeting() {
        return "Hello World!";
    }

}
