/**
 * This is a typical Repository
 * Created by osh on 25/10/17.
 */
package com.wemboo.boilerplate.db;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;


/**
 * The singleton annotation is required for {@link PersistenceContext} after {@link javax.inject.Inject}
 * empty bean.xml in META-INF is required to provide CDI
 */
@Singleton
public class SampleRepository {

    private final static int CONST_TYPE_ID = 1;

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager em;

    private TypedQuery<SampleEntity> createNamedQuery(String name) {
        return em.createNamedQuery(name, SampleEntity.class);
    }

    public List<SampleEntity> findAll() {

        log.debug("Method 'findAll' was called");
        TypedQuery<SampleEntity> query = createNamedQuery("SampleEntity.findAll");

        return query.getResultList();
    }

    public List<SampleEntity> findByType() {
        log.debug("Method 'findByType' was called");

        TypedQuery<SampleEntity> query = createNamedQuery("SampleEntity.findByType");
        query.setParameter("type", CONST_TYPE_ID);

        return query.getResultList();
    }    

    public long save(SampleEntity sampleEntity) {
        log.debug("Method 'save' was called with key: "+sampleEntity.getRecordId()+" and name: "+sampleEntity.getName());

        em.persist(sampleEntity);

        return sampleEntity.getRecordId();
    }    
}
          