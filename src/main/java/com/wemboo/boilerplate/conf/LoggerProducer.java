package com.wemboo.boilerplate.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * This is a typical thing to reduce boilerplate
 * Created by dima on 7/28/17.
 */
public class LoggerProducer {

    @Produces
    public Logger getLogger(final InjectionPoint ip)
    {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }
}