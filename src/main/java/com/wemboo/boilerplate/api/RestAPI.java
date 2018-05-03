/**
 * Created by dima on 8/10/16.
 * All React routes should be configured to index.html page here
 */
package com.wemboo.boilerplate.api;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(RestAPI.APIPATH)
public class RestAPI extends Application {

    static final String APIPATH = "/api";

}
