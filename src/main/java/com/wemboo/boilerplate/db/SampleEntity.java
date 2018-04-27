/**
 * Typical entity
 * @author osh
 */
package com.wemboo.boilerplate.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SAMPLE_TABLE")
@NamedQuery(name="SampleEntity.findAll", query="SELECT s FROM SampleEntity s")
public class SampleEntity implements Serializable{

	private static final long serialVersionUID = 4386150307257646576L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sample_entity_generator")
	@SequenceGenerator(name="sample_entity_generator", sequenceName = "sample_sequence_seq", allocationSize=1)	    
    @Column(name = "RECORD_ID")
    private long recordId;

    @Column(name = "NAME")
    private String name;

    public void setRecordId(long recordId){
        this.recordId = recordId;
    }

    public long getRecordId(){
        return this.recordId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
}