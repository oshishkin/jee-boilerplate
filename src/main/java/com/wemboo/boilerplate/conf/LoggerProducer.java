package com.wemboo.boilerplate.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * This is a typical thing to reduce boilerplate
 * Created by dima on 7/28/17.
 */
@Dependent
public class LoggerProducer {

    @Produces
    public Logger getLogger(final InjectionPoint ip)
    {
        Logger log = LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
        log.debug("Logger created!");
        return log;
    }

}