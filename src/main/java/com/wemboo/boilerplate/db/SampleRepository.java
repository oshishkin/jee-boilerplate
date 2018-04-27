/**
 * This is a typical Repository
 */
package com.wemboo.boilerplate.db;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;


@Stateless   //Stateless session bean annotation
@LocalBean   //No-interface view annotation
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
          