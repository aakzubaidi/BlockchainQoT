package com.iot.server.iotserver;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "alerts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Alert implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 92450654559059373L;


    @Schema(description = "Unique identifier of the alert.", 
    example = "1", required = true)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;


    @ElementCollection (fetch = FetchType.EAGER)
    Map <String, String> alertStatus;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    protected Alert () {
        alertStatus = new HashMap<String, String>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getAlertStatus() {
        return alertStatus;
    }


    public Sensor getSensor() {
        return sensor;
    }


    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }



    
}
